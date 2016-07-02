package com.screwfix.claim.statistics.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Denis on 6/19/2016
 */
@XmlRootElement(name = "hudson.plugins.claim.ClaimBuildAction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Action {
    private boolean claimed;
    private String claimedBy;
    private String reason;

    public boolean isClaimed() {
        return claimed;
    }

    public Action setClaimed(boolean claimed) {
        this.claimed = claimed;
        return this;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public String getReason() {
        return reason;
    }

    public Action setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Action setClaimedBy(String claimedBy) {
        this.claimedBy = claimedBy;
        return this;
    }

    @Override
    public String toString() {
        return "Action{" +
                "claimed=" + claimed +
                ", claimedBy='" + claimedBy + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
