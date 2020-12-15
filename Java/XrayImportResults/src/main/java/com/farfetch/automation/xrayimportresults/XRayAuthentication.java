/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.xrayimportresults;

import com.farfetch.automation.entities.JiraKeys;
import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Braulio Batista
 */
public class XRayAuthentication {

    /**
     * Do login Xray
     *
     * @param url
     * @param keys
     * @return
     * @throws IOException
     */
    public static String doLogin(String url, JiraKeys keys) throws IOException {

        String result = "";
        HttpPost post = new HttpPost(url);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"client_id\":\"{0}\",");
        json.append("\"client_secret\":\"{1}\"");
        json.append("}");

        String value = json.toString();
        value = value.replace("{0}", keys.getClientId()).replace("{1}", keys.getClientSecret());

        post.addHeader("content-type", "application/json");

        // add request parameters or form parameters
        post.setEntity(new StringEntity(value));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }
}
