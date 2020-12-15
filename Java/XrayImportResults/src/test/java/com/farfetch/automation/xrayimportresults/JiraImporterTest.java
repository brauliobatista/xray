/*
 * Copyright (c) 2020 Fashion Concierge
 * All rights reserved.
 */
package com.farfetch.automation.xrayimportresults;

import com.farfetch.automation.entities.JiraBody;
import java.util.Date;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Bráulio Batista
 */
public class JiraImporterTest {

    /**
     * Test of Import method, of class JiraImporter.
     *
     * @throws java.lang.Exception
     */
    @Test
    @Disabled
    public void testImport() throws Exception {
        JiraBody body = new JiraBody();
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;
        String completeDate = year + "-" + month + "-" + day;
        body.setDescription("My first import");
        body.setFinishDate(completeDate + "T18:32:19.747000+00:00");
        body.setStartDate(completeDate + "T18:11:49.606000+00:00");
        body.setSummary("Automated Test Run");
        body.setStart(completeDate + "T18:20:07.864000+00:00");
        body.setFinish(completeDate + "T18:20:31.094000+00:00");
        body.setStatus("PASSED");
        body.setComment("This is my test.");
        body.setTestKey("FC-2583");
        String value = JiraImporter.Import(body);
        assertTrue(value.contains("id"));
    }

    /**
     * Test of Import multipart method, of class JiraImporter.
     *
     * @throws Exception
     */
    @Test
    public void testImportMultipart() throws Exception {
        String[] results = {"src/test/resources/datasource/junitResult.xml"};
        String info = "src/test/resources/datasource/info-execution.json";
        String testInfo = "src/test/resources/datasource/info-test.json";
        String reportFormat = "junit";
        String value = JiraImporter.ImportMultipart(reportFormat, results, info, testInfo);
        assertTrue(value.contains("id"));
    }
}
