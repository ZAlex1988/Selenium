package com.solr.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//performs a single solr search from a randomly selected list of provided queries
public class Searcher {
    private static final Logger log = LogManager.getLogger("appLogger");
    private List<String> queries = Arrays.asList("directed_by:Gary*", "genre:\"Black comedy\"",
            "genre:Thriller","*:*", "numActors:[100 TO *]");
    private CloudSolrClient solr;
    private Random rand = new Random();

    public Searcher(CloudSolrClient client) {
        this.solr = client;
    }

    //return timeTakenToSearchInMillis
    public SearchStats search() {
        long startTime = System.currentTimeMillis();
        String query = queries.get(rand.nextInt(5));
        String status = runQuery(query);
        long endTime = System.currentTimeMillis();
        return new SearchStats(query, status, (endTime - startTime));
    }

    public String runQuery(String query) {
        String status = "Success";
        SolrQuery q = new SolrQuery(query)
                .setRows(100);
        try {
            QueryResponse resp = solr.query("Movies", q, SolrRequest.METHOD.POST);
            resp.getResults().forEach(res -> log.debug("Search Result : " + res.getFieldValueMap()));
        } catch (SolrServerException | IOException e) {
            log.error("Unable to execute query: ", e);
            status = "Failure " + e.getMessage();
        }
        return status;
    }


}
