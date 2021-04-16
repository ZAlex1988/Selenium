package com.solr.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SearchDriver {
    private static final Logger log = LogManager.getLogger("appLogger");
    private Executor executor = Executors.newSingleThreadExecutor();
    private CloudSolrClient solr;
    private Random rand = new Random();
    private List<SearchStats> stats = new ArrayList<>();
    boolean stop;
    private int numRunning;
    private int numSearches;
    private ExecutorService execService;
    private boolean allCompleted;


    public SearchDriver(int numSearches, CloudSolrClient solr) {
        execService = Executors.newFixedThreadPool(numSearches);
        this.solr = solr;
        this.numSearches = numSearches;
    }

    public void startSearches() {
        executor.execute(() -> {
            List<Future<SearchStats>> results = new ArrayList<>();
            while(! this.stop) {
                if (numRunning < numSearches) {
                    for (int i = 0; i < (numSearches - numRunning); i++) {
                        results.add(createFutureTask());
                    }
                }
                try {
                    Thread.sleep(rand.nextInt(3000)+2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                results.forEach(res -> {
                    if (res.isDone()) {
                        try {
                            stats.add(res.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                });

                results = results.stream().filter(res -> ! res.isDone()).collect(Collectors.toList());
                numRunning = results.size();
            }
            log.info("Results completed! ");
            allCompleted = true;
        });
    }

    private Future<SearchStats> createFutureTask() {
        Callable<SearchStats> callableTask = () -> {
            Searcher searcher = new Searcher(solr);
            SearchStats stats = searcher.search();
            return stats;
        };
        numRunning++;
        return execService.submit(callableTask);

    }

    public void stop() {
        log.info("Stopping Searcher...");
        this.stop = true;
    }

    public List<SearchStats> getStats() {
        while (! allCompleted) {
            try {
                log.info("Awaiting completion...");
                Thread.sleep(rand.nextInt(10000)+2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stats;
    }


}
