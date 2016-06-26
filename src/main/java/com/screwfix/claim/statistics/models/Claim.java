package com.screwfix.claim.statistics.models;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Claim {
    private static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd"); // TODO; remove to static constant
    private long id;
    private String jobName;
    private String user;
    private String reason;
    private LocalDateTime startClaim;
    private LocalDateTime endClaim;

    public String getJobName() {
        return jobName;
    }

    public Claim setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Claim setUser(String user) {
        this.user = user;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Claim setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getEndClaim() {
        return Optional.ofNullable(endClaim).isPresent() ? endClaim.format(FORMATTER) : "active";
    }

    public Claim setEndClaim(@Nullable LocalDateTime endClaim) {
        this.endClaim = endClaim;
        return this;
    }

    public String getStartClaim() {
        return startClaim.format(FORMATTER);
    }

    public Claim setStartClaim(LocalDateTime startClaim) {
        this.startClaim = startClaim;
        return this;
    }

    public long getId() {
        return id;
    }

    public Claim setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim Claim = (Claim) o;
        return id == Claim.id && !(jobName != null ? !jobName.equals(Claim.jobName) :
                Claim.jobName != null) && !(user != null ? !user.equals(Claim.user) :
                Claim.user != null) && !(reason != null ? !reason.equals(Claim.reason) :
                Claim.reason != null) && !(startClaim != null ? !startClaim.equals(Claim.startClaim) :
                Claim.startClaim != null) && !(endClaim != null ? !endClaim.equals(Claim.endClaim) :
                Claim.endClaim != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (startClaim != null ? startClaim.hashCode() : 0);
        result = 31 * result + (endClaim != null ? endClaim.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Claim{id=" + id + ", jobName='" + jobName + '\'' + ", user='"
                + user + '\'' + ", reason='" + reason + '\'' + ", startClaim="
                + startClaim + ", endClaim=" + endClaim + '}';
    }

}