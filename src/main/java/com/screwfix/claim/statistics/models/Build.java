package com.screwfix.claim.statistics.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Created by Denis on 6/19/2016
 */
@XmlRootElement(name = "build")
@XmlAccessorType(XmlAccessType.FIELD)
public class Build {

    private Actions actions;
    private String result;
    private LocalDateTime buildDate;

    public Actions getActions() {
        return actions;
    }

    public Build setActions(Actions actions) {
        this.actions = actions;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Build setResult(String result) {
        this.result = result;
        return this;
    }

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public Build setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
        return this;
    }

    @Override
    public String toString() {
        return "Build{actions=" + actions + ", result='" + result + '\'' + '}';
    }
}
