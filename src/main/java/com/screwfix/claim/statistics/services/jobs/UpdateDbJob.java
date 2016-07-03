package com.screwfix.claim.statistics.services.jobs;

import com.screwfix.claim.statistics.models.Build;
import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.services.DataTransformer;
import com.screwfix.claim.statistics.services.XmlLoader;
import com.screwfix.claim.statistics.services.dao.ClaimDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class UpdateDbJob implements Job {

    private XmlLoader xmlLoader;
    private DataTransformer transformer;
    private ClaimDao dao;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, List<Build>> builds = xmlLoader.loadXml();
        Map<String, List<Claim>> claims = transformer.buildsToClaims(builds);
    }

    @Inject
    public void setXmlLoader(XmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }

    @Inject
    public void setTransformer(DataTransformer transformer) {
        this.transformer = transformer;
    }

    @Inject
    public void setDao(ClaimDao dao) {
        this.dao = dao;
    }
}
