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

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
