package com.screwfix.claim.statistics.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.ofNullable;

/**
 * Created by Denis on 6/19/2016
 */
@XmlRootElement(name = "build")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildXml {

    private Actions actions;
    private String result;

    public List<Action> getActions() {
        return ofNullable(actions.getActions()).isPresent() ? actions.getActions() : newArrayList();
    }

    public BuildXml setActions(Actions actions) {
        this.actions = actions;
        return this;
    }

    public String getResult() {
        return result;
    }

    public BuildXml setResult(String result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "Build{actions=" + actions + ", result='" + result + '\'' + '}';
    }
}
