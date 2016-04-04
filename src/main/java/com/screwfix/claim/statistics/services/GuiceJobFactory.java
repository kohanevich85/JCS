package com.screwfix.claim.statistics.services;

import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.inject.Inject;

public class GuiceJobFactory implements JobFactory {

    private static final Logger LOGGER = Logger.getLogger(GuiceJobFactory.class);
    private final Injector guice;

    @Inject
    public GuiceJobFactory(final Injector guice) {
        this.guice = guice;
    }

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = triggerFiredBundle.getJobDetail();
        Class jobClass = jobDetail.getJobClass();
        try {
            return (Job) guice.getInstance(jobClass);
        } catch (Exception e) {
            LOGGER.error("Error occurred during guice DI initialization");
            throw new RuntimeException("Error occurred during guice DI initialization", e);
        }
    }
}
