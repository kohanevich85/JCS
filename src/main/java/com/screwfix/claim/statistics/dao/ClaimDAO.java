package com.screwfix.claim.statistics.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;

public interface ClaimDAO {
    //  TODO: test query
    Claim findClaimById(FilterParams params);
}
