package com.screwfix.claim.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class StatisticsConfiguration extends Configuration {

    @NotNull
    private String schedulerCron;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("schedulerCron")
    public String getSchedulerCron() {
        return schedulerCron;
    }

    @JsonProperty("schedulerCron")
    public void setSchedulerCron(String schedulerCron) {
        this.schedulerCron = schedulerCron;
    }
}