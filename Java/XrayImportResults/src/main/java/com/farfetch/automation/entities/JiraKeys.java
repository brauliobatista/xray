/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.entities;

/**
 *
 * @author Braulio Batista
 */
public class JiraKeys {

    private String client_id;
    private String client_secret;

    /**
     * set client id
     *
     * @param value
     */
    public void setClientId(String value) {
        this.client_id = value;
    }

    /**
     * set client secret
     *
     * @param value
     */
    public void setClientSecret(String value) {
        this.client_secret = value;
    }

    /**
     * get client id
     *
     * @return
     */
    public String getClientId() {
        return this.client_id;
    }

    /**
     * get client secret
     *
     * @return
     */
    public String getClientSecret() {
        return this.client_secret;
    }
}
