package com.solr.test;

public class Driver {

    public static void main(String [] args) {
        InsertDocs inserter = new InsertDocs();
//        inserter.clearCollection();
        inserter.insertBatch();
        inserter.closeSolrClient();

    }
}
