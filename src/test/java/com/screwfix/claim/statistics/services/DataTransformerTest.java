package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.models.Build;
import com.screwfix.claim.statistics.models.Claim;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.beust.jcommander.internal.Lists.newArrayList;
import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.*;

public class DataTransformerTest {

    private static final LocalDateTime _2016_01_01_00_00_00 = LocalDateTime.of(2016, 1, 1, 0, 0, 0);
    private static final LocalDateTime _2016_01_01_01_00_00 = LocalDateTime.of(2016, 1, 1, 1, 0, 0);
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

    @Test
    public void test() throws Exception {
        DataTransformer transformer = new DataTransformer();
        Map<String, List<Claim>> claims = transformer.buildsToClaims(JOBS);
        assertEquals(1, claims.get("job_1").size());
        assertNull(claims.get("job_1").get(0).getEndTimeClaim());
    }
}