package crawler;

import com.alibaba.fastjson.JSONObject;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import crawler.utils.RequestUtil;
import crawler.utils.RuoKuai;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/11/7.
 */
public class AnJuKe {
    static BASE64Encoder encoder = new BASE64Encoder();
    static AtomicInteger count = new AtomicInteger(1);
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
    static Semaphore semaphore = new Semaphore(1);
    static final DBCollection coll=new MongoClient( "192.168.0.61" , 27017 ).getDB( "FR" ).getCollection("anjuke_jiedu");
    public static void main(String[] args) {
         String url="https://xm.anjuke.com/";
         String result= RequestUtil.doGet(url, "utf-8");
         Document document= Jsoup.parse(result);
         Elements elements=document.select("#city-panel>dl>dd>a");
         for(int i=15;i<elements.size();i++){
             final String  city=elements.get(i).text();
             final String  link=elements.get(i).attr("href");
        /*     try {
                 semaphore.acquire();
                 fixedThreadPool.execute(new Runnable() {
                     public void run() {
                         doNext(city,link);
                     }
                 });
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }finally {
                 semaphore.release();
             }*/
             doNext(city,link);
         }
    }

    public  static  void  doNext(String city,String url){
          url=url+"community/?from=navigation";
          String result=RequestUtil.doGet(url,"utf-8");
          Document document= Jsoup.parse(result);
          if(document.select(".info>p").text().toString().contains("?????????з╓?")){
              System.out.println("??????????????");
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              String imagURL=document.select("#seccode").attr("src");
              String token=document.select("input[name=token]").val();
              String token2=document.select("input[name=token2]").val();
              String link="http://api.ruokuai.com/create.json";
           /*   Map<String,Object> map=new HashMap<String,Object>();
              map.put("username","fr123456");
              map.put("password","fangrui123456");
              map.put("typeid","3050");
              map.put("softid","92092");
              map.put("softkey","adb469e80fa34b4e8942e35b0cb9d8d7");
              map.put("image",image2byte(imagURL));
              List<NameValuePair> list=HttpClient.getListNameValuePairs(map);
              String getAnswert=RequestUtil.doPost(link,list);*/
              httpGetRequest();
              doNext(city,url);
          }
          Element element=document.select(".elems-l").get(0);
          Elements elements0=element.select("a");
          for(int i=1;i<elements0.size();i++){
              String region=elements0.get(i).text();
              String link=elements0.get(i).attr("href");
              doThid(city,region,link);
          }
    }

    public  static void doThid(String city,String region,String url){
        String result=RequestUtil.doGet(url,"utf-8");
        Document document= Jsoup.parse(result);
        Elements elements=document.select(".li-info>h3>a");
        for(Element element:elements){
            String  name=element.text();
            String  link= StringUtils.substringBefore(url, "/community")+element.attr("href");
            doFor(city,region,name,link);
        }
        boolean is=false;
        if(StringUtils.isNotBlank(document.select("a:contains(????)").toString())){
            is=true;
        }
        while(is){
            String lin=document.select("a:contains(????)").attr("href");
            String resultA=RequestUtil.doGet(lin,"utf-8");
            Document doc= Jsoup.parse(resultA);
            Elements elements0=doc.select(".li-info>h3>a");
            for(Element element:elements0){
                String  name=element.text();
                String  link= StringUtils.substringBefore(url, "/community")+element.attr("href");
                doFor(city,region,name,link);
            }
            if(StringUtils.isNotBlank(doc.select("a:contains(????)").toString())){
                is=true;
                document=doc;
            }else {
                is=false;
            }
        }
    }

    public  static  void doFor(String city,String region,String name,String url){
        try {
            //Thread.sleep(2000);
            String result = RequestUtil.doGet(url, "utf-8");
            Document document = Jsoup.parse(result);
            String link = document.select("a:contains(?????)").attr("href");
            if(StringUtils.isNotBlank(link)){
                String resultA = RequestUtil.doGet(link, "utf-8");
                Document doc = Jsoup.parse(resultA);
                Elements elements1 = doc.select(".info-character.good-character");
                Elements elements2 = doc.select(".info-character.bad-character>dd");
                List list = new ArrayList();
                for (int i = 0; i < elements1.size(); i++) {
                    Map map = new HashMap();
                    Elements elements = elements1.get(i).select("dd");
                    for (Element element : elements) {
                        String key = element.select("label").text();
                        String value = element.ownText();
                        map.put(key, value);
                    }
                    map.put("????", elements2.get(i).text());
                    list.add(map);
                }
                BasicDBObject basicDBObject = new BasicDBObject("city", city).append("region", region).append("name", name).append("list", list);
                DBObject basicDBObject1 = coll.findOne(new BasicDBObject("city", city).append("region", region).append("name", name));
                if (basicDBObject1 == null){
                    coll.save(basicDBObject);
                    System.out.println("?????"+count.get()+"??");
                    count.addAndGet(1);
                }
            }
        }catch (Exception e){

        }
    }

    public  static  void  httpGetRequest(){//????????
        String link="https://www.anjuke.com/captcha-verify/?callback=shield&from=antispam&history=aHR0cHM6Ly9zaGFuZ2hhaS5hbmp1a2UuY29tL3NhbGUv";
         WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);
         webClient.getOptions().setThrowExceptionOnScriptError(false);
         webClient.getOptions().setCssEnabled(false);
         webClient.getOptions().setTimeout(300000);
        try {
            HtmlPage page = (HtmlPage) webClient.getPage(link);
            HtmlImage valiCodeImg = (HtmlImage) page.getElementById("seccode");
            ImageReader imageReader = valiCodeImg.getImageReader();
            BufferedImage bufferedImage = imageReader.read(0);
            FileOutputStream os =  new FileOutputStream("D:\\1232.jpg");
            ImageIO.write(bufferedImage,"jpg", os);
            os.flush();
            os.close();
            String getAnswert= RuoKuai.createByPost("fr123456", "fangrui123456", "3050", "20000", "92092", "adb469e80fa34b4e8942e35b0cb9d8d7", getImageBinary("D:\\1232.jpg"));
            JSONObject jsonObject= JSONObject.parseObject(getAnswert);
            String picId= (String)jsonObject.get("Result");
            HtmlForm form = page.getFormByName("form");
            HtmlTextInput textCode = form.getInputByName("code");
            HtmlSubmitInput button = form.getInputByName("submit");
            textCode.setText(picId);
            //webClient.getPage("webClient").getWebResponse().getContentAsStream();
            button.click();
            System.out.println(page.getPage().toString());
            int j=1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] image2byte(String url){
        byte[] data = null;
        InputStream input = getInputStream(url);
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static InputStream getInputStream(String URL_PATH) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(URL_PATH);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // ?????????????????
            httpURLConnection.setConnectTimeout(3000);
            // ??????ио????????????????????
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // ????????????????????
                inputStream = httpURLConnection.getInputStream();

            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputStream;
    }
    public static byte[] getImageBinary(String Imgpath){
        File f = new File(Imgpath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
