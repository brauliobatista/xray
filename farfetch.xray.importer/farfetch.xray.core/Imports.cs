using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;

namespace farfetch.xray.core
{
    public class Imports
    {
        public static async Task<String> Import(String uri, String project, List<String> results, FrameworkTypes type, String fileName)
        {
            // Parsing url
            string url = uri + "/import/execution/" + type.ToString() + "?projectKey=" + project;

            using (var client = new HttpClient())
            {
                // setting multipart
                var formData = new MultipartFormDataContent();
                formData.Add(new StreamContent(new MemoryStream(File.ReadAllBytes(results[0]))), "results", fileName);
                var request = new HttpRequestMessage(HttpMethod.Post, url)
                {
                    Content = formData
                };

                // adding header
                request.Headers.Add("accept", "application/json");

                // setting token
                client.DefaultRequestHeaders.Add("Authorization", Authentication.GetToken());

                // Request
                var response = await client.SendAsync(request);
                if (response.IsSuccessStatusCode)
                {
                    return await response.Content.ReadAsStringAsync();
                }
                else
                {
                    throw new WebException($"The remote server returned unexpcted status code: {response.StatusCode} - {response.ReasonPhrase}.");
                }
            }
        }

        /// <summary>
        ///  Import MultiPart
        /// </summary>
        /// <param name="uri"></param>
        /// <param name="infoFile"></param>
        /// <param name="testInfoFile"></param>
        /// <param name="results"></param>
        /// <param name="type"></param>
        /// <returns></returns>
        public static async Task<String> ImportMultipart(String uri, String infoFile, String testInfoFile, List<String> results, FrameworkTypes type)
        {
            // Parsing url
            string url = uri + "/import/execution/" + type.ToString() + "/multipart";

            using (var client = new HttpClient())
            {
                // setting multipart
                var formData = new MultipartFormDataContent();
                formData.Add(new StreamContent(new MemoryStream(File.ReadAllBytes(infoFile))), "testInfo", "info-test.json");
                formData.Add(new StreamContent(new MemoryStream(File.ReadAllBytes(testInfoFile))), "info", "info-execution.json");
                formData.Add(new StreamContent(new MemoryStream(File.ReadAllBytes(results[0]))), "results", "FcPropertiesTest.xml");
                var request = new HttpRequestMessage(HttpMethod.Post, url)
                {
                    Content = formData
                };

                // adding header
                request.Headers.Add("accept", "application/json");

                // setting token
                client.DefaultRequestHeaders.Add("Authorization", Authentication.GetToken());

                // Request
                var response = await client.SendAsync(request);
                if (response.IsSuccessStatusCode)
                {
                    return await response.Content.ReadAsStringAsync();
                }
                else
                {
                    throw new WebException($"The remote server returned unexpcted status code: {response.StatusCode} - {response.ReasonPhrase}.");
                }       
            }
        }
    }
}