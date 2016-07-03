package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.models.Build;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertEquals;

public class XmlLoaderTest {

    private static final LocalDateTime _2016_01_01_00_00_00 = LocalDateTime.of(2016, 1, 1, 0, 0, 0);
    private static final LocalDateTime _2016_01_01_01_00_00 = LocalDateTime.of(2016, 1, 1, 1, 0, 0);
    private static final int ANALYZE_DAYS = 1000000;
    private static final Build BUILD_SUCCESS = new Build().setResult("SUCCESS")
                                                    .setJobName("job_1")
                                                    .setBuildDate(_2016_01_01_00_00_00);
    private static final Build BUILD_CLAIMED = new Build().setResult("FAILURE")
                                                    .setJobName("job_1")
                                                    .setBuildDate(_2016_01_01_01_00_00)
                                                    .setClaimed(true)
                                                    .setClaimedBy("user_1")
                                                    .setReason("something wrong");
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
        assertEquals(JOBS, result);
    }
}