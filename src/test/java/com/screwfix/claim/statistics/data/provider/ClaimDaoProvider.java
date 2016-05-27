package com.screwfix.claim.statistics.data.provider;

import com.screwfix.claim.statistics.services.dao.ClaimDaoImplTest;
import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;
import static java.time.Month.JANUARY;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Data Provider for {@link ClaimDaoImplTest}
 */
public class ClaimDaoProvider {
    private static final LocalDateTime _2015_01_12 = of(2015, JANUARY, 12, 0, 0);
    private static final LocalDateTime _2015_01_14 = of(2015, JANUARY, 14, 0, 0);
    private static final LocalDateTime _2015_01_16 = of(2015, JANUARY, 16, 0, 0);
    private static final Claim CLAIM_1 = new Claim().setId(1).setUser("user_1").setJobName("job_1").setReason("Reason_1").setStartClaim(_2015_01_12).setEndClaim(_2015_01_14);
    private static final Claim CLAIM_2 = new Claim().setId(2).setUser("user_2").setJobName("job_2").setReason("Reason_2").setStartClaim(_2015_01_12).setEndClaim(null);
    private static final Claim CLAIM_3 = new Claim().setId(3).setUser("user_3").setJobName("job_3").setReason("Reason_3").setStartClaim(_2015_01_12).setEndClaim(_2015_01_16);
    private static final FilterParams BY_JOB_NAME = new FilterParams().setJobName("job_1").setPage("1");
    private static final FilterParams BY_JOB_USER_NAME = new FilterParams().setJobName("ob_").setUser("us").setReason("Rea").setPage("1");
    private static final FilterParams FIRST_PAGE = new FilterParams().setItems("1").setPage("1");
    private static final FilterParams THIRD_PAGE = new FilterParams().setItems("1").setPage("3");
    private static final FilterParams SORT_BY_USER = new FilterParams().setSortField("claimed_by").setSortType("DESC");
    private static final FilterParams BY_DATE_FROM = new FilterParams().setDateFrom("01/11/2015");
    private static final FilterParams BY_FROM_TO_DATE = new FilterParams().setDateFrom("01/11/2015").setDateTo("01/14/2015");
    private static final FilterParams BY_DURATION = new FilterParams().setDuration("3").setIsActiveClaim("not_active");
    private static final FilterParams FIND_ANY_CLAIMS = new FilterParams().setIsActiveClaim("any");
    private static final FilterParams FIND_NON_ACTIVE_CLAIMS = new FilterParams().setIsActiveClaim("not_active");
    private static final FilterParams FIND_ACTIVE_CLAIMS = new FilterParams().setIsActiveClaim("active");

    public static Object[][] provideData() {
        return new Object[][]
        {
          //     1. filter               2. db result
                {BY_JOB_NAME,            singletonList(CLAIM_1)},
                {BY_JOB_USER_NAME,       asList(CLAIM_1, CLAIM_2, CLAIM_3)},
                {FIRST_PAGE,             asList(CLAIM_1, CLAIM_2)},
                {THIRD_PAGE,             singletonList(CLAIM_3)},
                {SORT_BY_USER,           asList(CLAIM_3, CLAIM_2, CLAIM_1)},
                {BY_DATE_FROM,           asList(CLAIM_1, CLAIM_2, CLAIM_3)},
                {BY_FROM_TO_DATE,        asList(CLAIM_1, CLAIM_2, CLAIM_3)},
                {BY_DURATION,            singletonList(CLAIM_3)},
                {FIND_ANY_CLAIMS,        asList(CLAIM_1, CLAIM_2, CLAIM_3)},
                {FIND_NON_ACTIVE_CLAIMS, asList(CLAIM_1, CLAIM_3)},
                {FIND_ACTIVE_CLAIMS,     singletonList(CLAIM_2)},
        };
    }
}
