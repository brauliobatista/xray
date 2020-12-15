/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.xrayimportresults;

import com.farfetch.automation.entities.JiraBody;
import com.farfetch.automation.entities.JiraKeys;
import java.io.IOException;

/**
 *
 * @author Braulio Batista
 */
public class JiraImporter {

    private static String clientId = "your client id";
    private static String clientSecret = "your client secret";

    /**
     * Import Xray Results
     *
     * @param body
     * @return
     * @throws IOException
     */
    public static String Import(JiraBody body) throws IOException, Exception {
        String baseUrl = "xray.cloud.xpand-it.com";
        String loginUrl = "https://{{baseUrl}}/api/v1/authenticate";
        loginUrl = loginUrl.replace("{{baseUrl}}", baseUrl);
        JiraKeys keys = new JiraKeys();
        keys.setClientId(clientId);
        keys.setClientSecret(clientSecret);

        String result = ImportResults.Import(baseUrl, XRayAuthentication.doLogin(loginUrl, keys), body);

        return result;
    }

    /**
     * Import Multipart
     *
     * @param reportFormat
     * @param results
     * @param info
     * @param testInfo
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String ImportMultipart(String reportFormat, String[] results, String info, String testInfo) throws IOException, Exception {
        String baseUrl = "xray.cloud.xpand-it.com";
        String loginUrl = "https://{{baseUrl}}/api/v1/authenticate";
        loginUrl = loginUrl.replace("{{baseUrl}}", baseUrl);
        JiraKeys keys = new JiraKeys();
        keys.setClientId(clientId);
        keys.setClientSecret(clientSecret);

        String result = ImportResults.ImportMultipart(baseUrl, XRayAuthentication.doLogin(loginUrl, keys), reportFormat, results, info, testInfo);

        return result;
    }
}
