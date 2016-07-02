package com.screwfix.claim.statistics.services;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableMap;
import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.models.Action;
import com.screwfix.claim.statistics.models.Actions;
import com.screwfix.claim.statistics.models.Build;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class XmlLoaderTest {

    private static final int ANALYZE_DAYS = 1000000;
    private static final Build BUILD_SUCCESS = prepareBuildSuccess();
    private static final Build BUILD_CLAIMED = prepareBuildClaimed();
    private static final Map<String, List<Build>> JOBS =
            newHashMap(of("job_1", newArrayList(BUILD_SUCCESS, BUILD_CLAIMED)));
    private XmlLoader loader;

    @Before
    public void setUp() throws URISyntaxException {
        loader = new XmlLoader();
        URI uri = XmlLoaderTest.class.getClassLoader().getResource("jobs").toURI();
        StatisticsConfiguration configuration = new StatisticsConfiguration();
        configuration.setJobsPath(Paths.get(uri).toString());
        configuration.setAnalyzeDays(ANALYZE_DAYS);
        loader.setConfiguration(configuration);
    }

    @Test
    public void test() throws Exception {
        Map<String, List<Build>> result = loader.loadXml();
        /*assertEquals(JOBS, result);*/
        assertEquals(1, result.size());
        assertEquals(2, result.get("job_1").size());
    }

    private static Build prepareBuildSuccess() {
        return new Build().setResult("SUCCESS").setActions(new Actions());
    }

    private static Build prepareBuildClaimed() {
        Actions actions = new Actions().setActions(asList(new Action().setClaimed(true).setReason("something wrong").setClaimedBy("user_1")));
        return new Build().setResult("FAILURE").setActions(actions);
    }
}