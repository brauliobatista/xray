/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.entities;

/**
 *
 * @author Braulio Batista
 */
public class JiraBody {

    private String description;
    private String finishDate;
    private String startDate;
    private String summary;
    private String start;
    private String finish;
    private String status;
    private String testKey;
    private String comment;

    /**
     * set description
     *
     * @param value
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * set finishDate
     *
     * @param value
     */
    public void setFinishDate(String value) {
        this.finishDate = value;
    }

    /**
     * set startDate
     *
     * @param value
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * set summary
     *
     * @param value
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * set start
     *
     * @param value
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * set finish
     *
     * @param value
     */
    public void setFinish(String value) {
        this.finish = value;
    }

    /**
     * set status
     *
     * @param value
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * set testKey
     *
     * @param value
     */
    public void setTestKey(String value) {
        this.testKey = value;
    }

    /**
     * set comment
     *
     * @param value
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * get description
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get finishDate
     *
     * @return
     */
    public String getFinishDate() {
        return this.finishDate;
    }

    /**
     * get startDate
     *
     * @return
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * get summary
     *
     * @return
     */
    public String getSummary() {
        return this.summary;
    }

    /**
     * get start
     *
     * @return
     */
    public String getStart() {
        return this.start;
    }

    /**
     * get finish
     *
     * @return
     */
    public String getFinish() {
        return this.finish;
    }

    /**
     * get client secret
     *
     * @return
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * get testKey
     *
     * @return
     */
    public String getTestKey() {
        return this.testKey;
    }

    /**
     * get comment
     *
     * @return
     */
    public String getComment() {
        return this.comment;
    }
}
