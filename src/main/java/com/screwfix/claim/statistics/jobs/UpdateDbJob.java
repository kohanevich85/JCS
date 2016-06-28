package com.screwfix.claim.statistics.jobs;

import com.screwfix.claim.statistics.models.Build;
import com.screwfix.claim.statistics.services.XmlLoader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class UpdateDbJob implements Job {

    private XmlLoader xmlLoader;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, List<Build>> stringListMap = xmlLoader.loadXml();
    }

    @Inject
    public void setXmlLoader(XmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }
}
