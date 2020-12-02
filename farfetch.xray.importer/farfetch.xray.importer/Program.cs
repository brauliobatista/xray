using farfetch.xray.core;
using farfetch.xray.entities;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace farfetch.xray.importer
{
    class Program
    {
        static void Main(string[] args)
        {
            String url = ConfigurationManager.AppSettings["url"];
            JiraKeys keys = new JiraKeys();
            keys.client_id = ConfigurationManager.AppSettings["xrayClientId"];
            keys.client_secret = ConfigurationManager.AppSettings["xrayClientSecret"];

            String param = JsonConvert.SerializeObject(keys);

            //Task<String> token = Authentication.DoLogin(url, param);
            //Console.WriteLine(token.Result);

            //string infoFile = Path.Combine(Environment.CurrentDirectory, @"DataSource\Sample", "info-test.json");
            //string infoExecution = Path.Combine(Environment.CurrentDirectory, @"DataSource\Sample", "info-execution.json");
            //string result = Path.Combine(Environment.CurrentDirectory, @"DataSource\Sample\junit", "TEST-com.fc.automation.core.utils.FilesUtilsTest.xml");

            // files path
            string infoFile = @"";
            string infoExecution = @"";
            string result = @"";
            String fileName = "";

            List<String> results = new List<string>();
            results.Add(result);

            Task<String> response = Imports.ImportMultipart(url, infoFile, infoExecution, results, FrameworkTypes.junit);
            Console.WriteLine(response.Result);

            Task<String> resp = Imports.Import(url, "FC", results, FrameworkTypes.junit, fileName);
            Console.WriteLine(resp.Result);

            Console.ReadLine();
        }
    }
}
