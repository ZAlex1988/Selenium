package com.solr.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MainDriver {

    private static final Logger log = LogManager.getLogger("appLogger");

    public static void main(String [] args) {

        //config params
        int docsToInsert = 4000000;
        int numParallelSearchesDringInsert = 15;
        String userName = "slr";
        String password = "Str0ngP@s5";


        //create objects
        InsertDocs inserter = new InsertDocs(userName, password);
        SearchDriver driver = new SearchDriver(numParallelSearchesDringInsert, inserter.getSolr());

        //start inserting and searching documents
        inserter.asyncInsert(docsToInsert);
        driver.startSearches();

        log.info("Driver is inserting and doing searches...");
        while (! inserter.isParallelDone()) {
            try {
                log.debug("Awaiting completion...");
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.stop();

        log.info("Insert taken time (seconds) " + inserter.getParallelTotalTime());
        List<SearchStats> stats = driver.getStats();

        log.info("Number of searches executed: " + stats.size());
        long numFailed = stats.stream().filter(res -> ! "Success".equalsIgnoreCase(res.getStatus())).count();
        List<Long> allMillis = stats.stream().map(SearchStats::getTimeTaken).collect(Collectors.toList());
        OptionalDouble averageTime = allMillis.stream().mapToLong(Long::longValue).average();

        log.info("Number of Searches failed " + numFailed);
        log.info("Average Search Time " + (averageTime.isPresent() ? averageTime.toString() : "null"));

        inserter.closeSolrClient(); //close Solr

        log.info("Done!");


    }
}
