package com.screwfix.claim.statistics.models;

import java.time.LocalDateTime;

/**
 * Created by Denis on 7/2/2016
 */
public class Build {

    private LocalDateTime buildDate;
    private String result;
    private boolean isClaimed;
    private String claimedBy;
    private String reason;

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public Build setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Build setResult(String result) {
        this.result = result;
        return this;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public Build setClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
        return this;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public Build setClaimedBy(String claimedBy) {
        this.claimedBy = claimedBy;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Build setReason(String reason) {
        this.reason = reason;
        return this;
    }

    @Override
    public String toString() {
        return "Build{" +
                "buildDate=" + buildDate +
                ", result='" + result + '\'' +
                ", isClaimed=" + isClaimed +
                ", claimedBy='" + claimedBy + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Build build = (Build) o;
        if (isClaimed != build.isClaimed) return false;
        if (buildDate != null ? !buildDate.equals(build.buildDate) : build.buildDate != null) return false;
        if (claimedBy != null ? !claimedBy.equals(build.claimedBy) : build.claimedBy != null) return false;
        if (reason != null ? !reason.equals(build.reason) : build.reason != null) return false;
        if (result != null ? !result.equals(build.result) : build.result != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result1 = buildDate != null ? buildDate.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (isClaimed ? 1 : 0);
        result1 = 31 * result1 + (claimedBy != null ? claimedBy.hashCode() : 0);
        result1 = 31 * result1 + (reason != null ? reason.hashCode() : 0);
        return result1;
    }
}
