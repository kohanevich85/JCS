package com.screwfix.claim.statistics.services;

import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;

import javax.inject.Inject;

public class QuartzManager implements Managed {

    private Scheduler scheduler;
    private JobConfigurator jobConfigurator;

    @Override
    public void start() throws Exception {
        scheduler.start();
        jobConfigurator.startSimpleJob();
    }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener("simple-job");
        scheduler.shutdown();
    }

    @Inject
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Inject
    public void setJobConfigurator(JobConfigurator jobConfigurator) {
        this.jobConfigurator = jobConfigurator;
    }
}
