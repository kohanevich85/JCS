package com.screwfix.claim.statistics.services.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;

import java.util.List;

public interface ClaimDao {
    List<Claim> findClaims(FilterParams params);
}
