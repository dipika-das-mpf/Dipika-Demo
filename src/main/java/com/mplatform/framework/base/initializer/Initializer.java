package com.mplatform.framework.base.initializer;

/**
 * Created by mohamed.abdulkadar on 6/16/2017.
 */
public class Initializer {

    private DbClients dbMetaMariaClient = null;
    private DbClients dbPortalMariaClient = null;
    private DbClients dbVerticaBackendClient = null;
    private DbClients dbVerticaReportingClient = null;
    private CouchbaseClient couchbaseClient = null;
    private HttpClient httpClient = null;

    public Initializer(DbClients dbMetaMariaClient,DbClients dbPortalMariaClient,
                       DbClients dbVerticaBackendClient,DbClients dbVerticaReportingClient,
                       CouchbaseClient couchbaseClient,HttpClient httpClient) {
        this.dbMetaMariaClient = dbMetaMariaClient;
        this.dbPortalMariaClient = dbPortalMariaClient;
        this.dbVerticaBackendClient = dbVerticaBackendClient;
        this.dbVerticaReportingClient = dbVerticaReportingClient;
        this.couchbaseClient = couchbaseClient;
        this.httpClient=httpClient;
    }






    public CouchbaseClient getCouchbaseClient() {
        return this.couchbaseClient;
    }

    public void setCouchbaseClient(CouchbaseClient couchbaseClient) {
        this.couchbaseClient = couchbaseClient;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public DbClients getDbMetaMariaClient() {
        return dbMetaMariaClient;
    }

    public void setDbMetaMariaClient(DbClients dbMetaMariaClient) {
        this.dbMetaMariaClient = dbMetaMariaClient;
    }

    public DbClients getDbPortalMariaClient() {
        return dbPortalMariaClient;
    }

    public void setDbPortalMariaClient(DbClients dbPortalMariaClient) {
        this.dbPortalMariaClient = dbPortalMariaClient;
    }

    public DbClients getDbVerticaBackendClient() {
        return dbVerticaBackendClient;
    }

    public void setDbVerticaBackendClient(DbClients dbVerticaBackendClient) {
        this.dbVerticaBackendClient = dbVerticaBackendClient;
    }

    public DbClients getDbVerticaReportingClient() {
        return dbVerticaReportingClient;
    }

    public void setDbVerticaReportingClient(DbClients dbVerticaReportingClient) {
        this.dbVerticaReportingClient = dbVerticaReportingClient;
    }
}
