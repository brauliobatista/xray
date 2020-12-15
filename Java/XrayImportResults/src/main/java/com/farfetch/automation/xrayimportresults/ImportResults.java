/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.xrayimportresults;

import com.farfetch.automation.entities.JiraBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Braulio Batista
 */
public class ImportResults {

    /**
     * Import Jira
     *
     * @param baseUrl
     * @param token
     * @param body
     * @return
     * @throws IOException
     */
    public static String Import(String baseUrl, String token, JiraBody body) throws IOException {

        String result = "";
        String url = "https://{{xrayHost}}/api/v1/import/execution";
        url = url.replace("{{xrayHost}}", baseUrl);
        HttpPost post = new HttpPost(url);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"info\":");
        json.append("{\"description\":\"{0}\",");
        json.append("\"finishDate\":\"{1}\",");
        json.append("\"startDate\":\"{2}\",");
        json.append("\"summary\":\"{3}\"");
        json.append("},");
        json.append("\"tests\":");
        json.append("[{\"start\":\"{4}\",");
        json.append("\"finish\":\"{5}\",");
        json.append("\"status\":\"{6}\",");
        json.append("\"testKey\":\"{7}\",");
        json.append("\"comment\":\"{8}\"");
        json.append("}]");
        json.append("}");

        String value = json.toString();
        value = value.replace("{0}", body.getDescription()).replace("{1}", body.getFinishDate()).replace("{2}", body.getStartDate())
            .replace("{3}", body.getSummary()).replace("{4}", body.getStart()).replace("{5}", body.getFinish())
            .replace("{6}", body.getStatus()).replace("{8}", body.getComment()).replace("{7}", body.getTestKey());

        String newToken = "Bearer" + token.replace('"', ' ');
        post.addHeader("content-type", "application/json");
        post.addHeader("Authorization", newToken);

        // add request parameters or form parameters
        post.setEntity(new StringEntity(value));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

    /**
     * Import multipart Files
     *
     * @param baseUrl
     * @param token
     * @param reportFormat
     * @param results
     * @param info
     * @param testInfo
     * @return
     * @throws IOException
     */
    public static String ImportMultipart(String baseUrl, String token, String reportFormat, String[] results, String info, String testInfo) throws IOException {

        String result = "";
        String url = "https://{{xrayHost}}/api/v1/import/execution/{{report}}/multipart";
        url = url.replace("{{xrayHost}}", baseUrl);
        url = url.replace("{{report}}", reportFormat);
        String newToken = "Bearer" + token.replace('"', ' ');

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        uploadFile.addHeader("multipart/form-data", "application/json");
        uploadFile.addHeader("Authorization", newToken);

        File fResults = new File(results[0]);
        builder.addBinaryBody(
            "results",
            new FileInputStream(fResults),
            ContentType.APPLICATION_OCTET_STREAM,
            fResults.getName()
        );

        File fInfo = new File(info);
        builder.addBinaryBody(
            "info",
            new FileInputStream(fInfo),
            ContentType.APPLICATION_OCTET_STREAM,
            fInfo.getName()
        );

        File fTestInfo = new File(testInfo);
        builder.addBinaryBody(
            "testInfo",
            new FileInputStream(fTestInfo),
            ContentType.APPLICATION_OCTET_STREAM,
            fTestInfo.getName()
        );

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();

        result = EntityUtils.toString(responseEntity);

        return result;
    }
}
