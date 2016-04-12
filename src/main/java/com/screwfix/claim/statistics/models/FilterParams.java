package com.screwfix.claim.statistics.models;

import javax.ws.rs.FormParam;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.Integer.valueOf;

public class FilterParams {
    private static final int MAX_PER_PAGE = 10;

    @FormParam("page")          private String page = "";
    @FormParam("items")         private String items = "";
    @FormParam("sortField")     private String sortField = "";
    @FormParam("sortType")      private String sortType = "";

    @FormParam("user")          private String user = "";
    @FormParam("jobName")       private String jobName = "";
    @FormParam("reason")        private String reason = "";
    @FormParam("isActiveClaim") private String isActiveClaim = "";
    @FormParam("duration")      private String duration = "";
    @FormParam("dateFrom")      private String dateFrom = "";
    @FormParam("dateTo")        private String dateTo = "";

    public String getPage() {
        return page;
    }

    public FilterParams setPage(String page) {
        this.page = page;
        return this;
    }

    public String getItems() {
        return items;
    }

    public FilterParams setItems(String items) {
        this.items = items;
        return this;
    }

    public String getSortField() {
        return sortField;
    }

    public FilterParams setSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public String getSortType() {
        return sortType;
    }

    public FilterParams setSortType(String sortType) {
        this.sortType = sortType;
        return this;
    }

    public String getUser() {
        return user;
    }

    public FilterParams setUser(String user) {
        this.user = user;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public FilterParams setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getIsActiveClaim() {
        return isActiveClaim;
    }

    public FilterParams setIsActiveClaim(String isActiveClaim) {
        this.isActiveClaim = isActiveClaim;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public FilterParams setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public FilterParams setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public String getDateTo() {
        return dateTo;
    }

    public FilterParams setDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public FilterParams setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public int getOffset() {
        int iPage = valueOf(page);
        return iPage == 0 ? 0 :
                (isNullOrEmpty(items) ? (iPage - 1) * MAX_PER_PAGE : (iPage - 1) * valueOf(items));
    }

    public int getLimit() {
        return isNullOrEmpty(items) ? MAX_PER_PAGE + 1 : valueOf(items) + 1;
    }

    @Override // TODO: remove?
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
