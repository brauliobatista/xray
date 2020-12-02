using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace farfetch.xray.entities
{
    public class JiraKeys
    {
        #region Private properties
        private String xrayClientId;
        private String xrayClientSecret;
        #endregion

        #region Public properties

        /// <summary>
        ///  the XrayClientId
        /// </summary>
        public String client_id
        {
            get { return xrayClientId; }

            set { xrayClientId = value; }
        }


        /// <summary>
        ///  the xrayClientSecret
        /// </summary>
        public String client_secret
        {
            get { return xrayClientSecret; }

            set { xrayClientSecret = value; }
        }

        #endregion
    }
}
