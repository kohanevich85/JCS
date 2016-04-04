package com.screwfix.claim.statistics.jobs;

import com.screwfix.claim.statistics.services.XmlLoader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;

public class UpdateDbJob implements Job {

    private XmlLoader xmlLoader;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        xmlLoader.loadXml();
    }

    @Inject
    public void setXmlLoader(XmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }
}
