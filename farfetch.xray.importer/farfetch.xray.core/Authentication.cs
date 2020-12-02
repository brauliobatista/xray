using farfetch.xray.entities;
using Newtonsoft.Json;
using System;
using System.Configuration;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace farfetch.xray.core
{
    public static class Authentication
    {
        private static HttpClient client = new HttpClient();

        /// <summary>
        ///  Do Login
        /// </summary>
        /// <param name="uri"></param>
        /// <param name="postParams"></param>
        /// <returns> token </returns>
        public static async Task<String>  DoLogin(String uri, String postParams )
        {
            string token = string.Empty;

            // Setting the route
            uri = uri + "authenticate";

            // Wrap our JSON inside a StringContent which then can be used by the HttpClient class
            var httpContent = new StringContent(postParams, Encoding.UTF8, "application/json");

            using (var httpClient = new HttpClient())
            {
                // Do the actual request and await the response
                var httpResponse = await httpClient.PostAsync(uri, httpContent);

                // If the response contains content we want to read it!
                if (httpResponse.Content != null)
                {
                     token = await httpResponse.Content.ReadAsStringAsync();
                }
            }

            return token;
        }

        /// <summary>
        /// Get Token 
        /// </summary>
        /// <returns> token</returns>
        public static String GetToken()
        {
            JiraKeys keys = new JiraKeys();

            // Getting external parameters
            String url = ConfigurationManager.AppSettings["url"];
            keys.client_id = ConfigurationManager.AppSettings["xrayClientId"];
            keys.client_secret = ConfigurationManager.AppSettings["xrayClientSecret"];

            // Convert object to json
            String param = JsonConvert.SerializeObject(keys);

            // Do login 
            Task<String> token = Authentication.DoLogin(url, param);

            // return token
            return String.Format("Bearer {0}", token.Result.Replace('"',' '));
        }
    }
}
