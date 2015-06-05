using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SistemasRecomendacao.Models;
using System.Net;
using System.IO;

namespace SistemasRecomendacao.Controllers
{
    public class HomeController : Controller
    {
        //
        // GET: 

        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Login()
        {
            return View();
        }

        public ActionResult About()
        {
            return View();
        }

        public ActionResult Contact()
        {
            return View();
        }

        public ActionResult LoginMobile()
        {
            return View();
        }

        [HttpPost]
        public ActionResult GerarPlayList(string userName)
        {
            var retorno = string.Empty;

            var request = (HttpWebRequest)WebRequest.Create("http://localhost:8080/musicRecommender/users/"+userName+"/recommendation?limit=20");

            request.Method = WebRequestMethods.Http.Get;
            request.Connection = "application/json";

            var response = (HttpWebResponse)request.GetResponse();

            using (StreamReader reader = new StreamReader(response.GetResponseStream()))
            {
                retorno = reader.ReadToEnd();
                reader.Close();
            }

            var songDto = Newtonsoft.Json.JsonConvert.DeserializeObject<List<Song>>(retorno);


            response.Close();
            return View(songDto);
        }
    }
}
