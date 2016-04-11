package com.screwfix.claim.statistics.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;

import java.util.List;

public interface ClaimDAO {
    List<Claim> findClaims(FilterParams params);
}
