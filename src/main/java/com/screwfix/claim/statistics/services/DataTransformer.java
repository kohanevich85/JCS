package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.models.Build;
import com.screwfix.claim.statistics.models.Claim;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Maps.newHashMap;
import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;

/**
 * Created by Denis on 7/3/2016
 */
public class DataTransformer {

    public Map<String, List<Claim>> buildsToClaims(Map<String, List<Build>> buildsMap) {
        Map<String, List<Claim>> result = newHashMap();
        buildsMap.forEach((jobName, builds) -> builds.stream().forEach(build -> {
            List<Claim> claims = result.get(jobName);
            if (!build.isClaimed()) {
                if (wasClaimActive(claims)) {
                    closeClaim(build.getBuildDate(), claims);
                }
            } else if (isFirstClaim(claims, build)) {
                result.put(jobName, newArrayList(buildToClaim(build)));
            } else if (isNewClaim(claims, build)) {
                result.get(jobName).add(buildToClaim(build));
            } else if (shouldBeReopened(claims, build)) {
                closeClaim(build.getBuildDate(), claims);
                result.get(jobName).add(buildToClaim(build));
            }
        }));
        return result;
    }

    private Claim buildToClaim(Build build) {
        return new Claim().setJobName(build.getJobName())
                .setReason(build.getReason())
                .setStartClaim(build.getBuildDate())
                .setUser(build.getClaimedBy());
    }

    private boolean shouldBeReopened(List<Claim> claims, Build build) {
        return !getLast(claims).getUser().equals(build.getClaimedBy()) ||
            !getLast(claims).getReason().equals(build.getReason());
    }

    private boolean isNewClaim(List<Claim> claims, Build build) {
        return build.isClaimed() && getLast(claims).getEndClaim() != null;
    }

    private boolean isFirstClaim(List<Claim> claims, Build build) {
        return build.isClaimed() && claims == null;
    }


    private boolean wasClaimActive(List<Claim> claims) {
        return claims != null && claims.size() > 0 && getLast(claims).getEndTimeClaim() == null;
    }

    private void closeClaim(LocalDateTime end, List<Claim> claims) {
        getLast(claims).setEndClaim(end);
    }
}
