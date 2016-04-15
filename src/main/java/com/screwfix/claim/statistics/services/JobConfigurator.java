package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.services.jobs.UpdateDbJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import javax.inject.Inject;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobConfigurator {

    private Scheduler scheduler;
    private StatisticsConfiguration configuration;

    public void startSimpleJob() throws SchedulerException {

        String cron = configuration.getSchedulerCron();
        Trigger trigger = newTrigger()
                .withIdentity("simple-trigger", "scheduler-group")
                .withSchedule(cronSchedule(cron))
                .forJob("simple-job", "scheduler-group")
                .build();

        JobDetail scheduledDbUpdateJob = newJob(UpdateDbJob.class)
                .withIdentity("simple-job", "scheduler-group")
                .build();
        scheduler.scheduleJob(scheduledDbUpdateJob, trigger);
    }

    @Inject
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Inject
    public void setConfiguration(StatisticsConfiguration configuration) {
        this.configuration = configuration;
    }
}
