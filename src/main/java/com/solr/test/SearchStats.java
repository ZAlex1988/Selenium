package com.solr.test;

public class SearchStats {

    private String queryExecuted;
    private String status;
    private Long timeTaken;

    public SearchStats(String queryExecuted, String status, Long timeTaken) {
        this.setQueryExecuted(queryExecuted);
        this.setStatus(status);
        this.setTimeTaken(timeTaken);
    }

    public String getQueryExecuted() {
        return queryExecuted;
    }

    public void setQueryExecuted(String queryExecuted) {
        this.queryExecuted = queryExecuted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public String toString() {
        return "SearchStats{" +
                "queryExecuted='" + queryExecuted + '\'' +
                ", status='" + status + '\'' +
                ", timeTaken=" + timeTaken +
                '}';
    }
}
