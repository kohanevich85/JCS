package com.screwfix.claim.statistics.services.health;

import com.codahale.metrics.health.HealthCheck;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import javax.inject.Inject;

public class QuartzHealthCheck extends HealthCheck {

    private final Scheduler scheduler;

    @Inject
    public QuartzHealthCheck(Scheduler scheduler)  throws SchedulerException {
        this.scheduler = scheduler;
    }

    @Override
    protected Result check() throws Exception {
        if (!scheduler.isStarted())
            return Result.unhealthy("The Quartz is currently in an error state");
        else
            return Result.healthy();
    }


}
