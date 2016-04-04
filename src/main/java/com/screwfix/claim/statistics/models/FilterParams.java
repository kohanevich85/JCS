package com.screwfix.claim.statistics.models;

import com.google.common.base.Strings;

import javax.ws.rs.FormParam;

import static com.google.common.base.Strings.isNullOrEmpty;

public class FilterParams {

    @FormParam("page")          private String page;
    @FormParam("items")         private String items;
    @FormParam("sortField")     private String sortField;
    @FormParam("sortType")      private String sortType;

    @FormParam("user")          private String user;
    @FormParam("jobName")       private String jobName;
    @FormParam("reason")        private String reason;
    @FormParam("isActiveClaim") private String isActiveClaim;
    @FormParam("duration")      private String duration;
    @FormParam("dateFrom")      private String dateFrom;
    @FormParam("dateTo")        private String dateTo;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getIsActiveClaim() {
        return isActiveClaim;
    }

    public void setIsActiveClaim(String isActiveClaim) {
        this.isActiveClaim = isActiveClaim;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "FilterParams{" +
                "page='" + page + '\'' +
                ", items='" + items + '\'' +
                ", sortField='" + sortField + '\'' +
                ", sortType='" + sortType + '\'' +
                ", user='" + user + '\'' +
                ", jobName='" + jobName + '\'' +
                ", isActiveClaim='" + isActiveClaim + '\'' +
                ", duration='" + duration + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                '}';
    }
}
