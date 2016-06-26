package com.screwfix.claim.statistics.models;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Denis on 6/19/2016
 */
@XmlRootElement(name = "build")
@XmlAccessorType(XmlAccessType.FIELD)
public class Build {


    private Actions actions;
    private long startTime;
    private String result;
    //private LocalDateTime buildDate;

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Build{" +
                "actions=" + actions +
                ", startTime='" + startTime + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
