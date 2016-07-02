package com.screwfix.claim.statistics.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Denis on 6/19/2016
 */
@XmlRootElement(name = "actions")
public class Actions {

    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    @XmlElement(name = "hudson.plugins.claim.ClaimBuildAction")
    public Actions setActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    @Override
    public String toString() {
        return "Actions{actions=" + actions + '}';
    }
}
