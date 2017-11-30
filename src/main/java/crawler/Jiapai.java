package crawler;

import com.google.common.collect.ImmutableList;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public class Jiapai implements PageProcessor {
    MongoClient mongoClient = new MongoClient( "192.168.0.61" , 27017 );
    DB db = mongoClient.getDB( "mantanghong" );
    DBCollection coll = db.getCollection("jiapai");
    int j=1;
    static int m=19;
    int mainPage=1;
    List<String[]> httpProxyPool = ImmutableList.of(
            new String[]{"192.168.0.90", "3234"},
            new String[]{"192.168.0.93", "3234"},
            new String[]{"192.168.0.94", "3234"},
            new String[]{"192.168.0.96", "3234"},
            new String[]{"192.168.0.98", "3234"},
            new String[]{"192.168.0.99", "3234"},
            new String[]{"192.168.0.100", "3234"},
            new String[]{"192.168.0.101", "3234"},
            new String[]{"192.168.0.102", "3234"},
            new String[]{"192.168.0.103", "3234"}
    );
    public void process(Page page) {
        String url = page.getRequest().getUrl();
        Document doc = Jsoup.parse(page.getRawText());
        if(url.matches("http://www.shjiapai.cn/Fyzs/index/qyid/\\d+/p/\\d+.html")){
            Elements elements=doc.select("dd>a");
            for(Element element:elements){
                String link= "http://www.shjiapai.cn"+element.attr("href");
                Request request = new Request(link);
                page.addTargetRequest(request);
            }
            if(StringUtils.isNotBlank(doc.select("a:contains(下一页)").toString())){
                mainPage++;
                System.out.println("进入主页第"+mainPage+"页抓取！");
                String nextPageUrl ="http://www.shjiapai.cn"+doc.select("a:contains(下一页)").first().attr("href");
                Request request = new Request(nextPageUrl);
                page.addTargetRequest(request);
            }
        }
        else if(url.matches("http://www.shjiapai.cn/Fyzs/content/id/\\d+.html")){
            Elements elements=doc.select(".title_2");
            String city="上海";
            String region=null;
            if(m==4){
                region="普陀";
            }
            else if(m==5){
                region="长宁";
            }
            else if(m==6){
                region="杨浦";
            }
            else if(m==7){
                region="松江";
            }
            else if(m==8){
                region="嘉定";
            }
            else if(m==9){
                region="虹口";
            }
            else if(m==10){
                region="闸北";
            }
            else if(m==11){
                region="静安";
            }
            else if(m==12){
                region="黄浦";
            }
            else if(m==13){
                region="青浦";
            }
            else if(m==14){
                region="南汇";
            }
            else if(m==15){
                region="奉贤";
            }
            else if(m==16){
                region="金山";
            }
            else if(m==17){
                region="崇明";
            }
            else if(m==19){
                region="浦东";
            }

            String district_name=elements.get(2).text();
            String loupan_address=elements.get(3).text();
            String huxing=elements.get(5).text();
            String area=elements.get(4).text();
            String wuye_type=elements.get(11).text();
            String chaoxiang=elements.get(7).text();
            String decoration=elements.get(10).text();
            String avg_price=elements.get(1).text();
            String sum_price=elements.get(0).text();
            String current_ster=elements.get(6).text();
            String build_year=elements.get(8).text();
            DBObject dbObject=new BasicDBObject();
            dbObject.put("city",city);
            dbObject.put("region",region);
            dbObject.put("district_name",district_name);
            dbObject.put("loupan_address",loupan_address);
            dbObject.put("huxing",huxing);
            dbObject.put("wuye_type",wuye_type);
            dbObject.put("chaoxiang",chaoxiang);
            dbObject.put("decoration",decoration);
            dbObject.put("avg_price",avg_price);
            dbObject.put("sum_price",sum_price);
            dbObject.put("current_ster",current_ster);
            dbObject.put("build_year",build_year);
            dbObject.put("area",area);
            coll.save(dbObject);
            System.out.println("********************已插入" +j+ "条数据****************");
            j++;
        }
    }

    public Site getSite() {
        return Site.me()
                .setCharset("utf-8")
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0")
                .setCycleRetryTimes(3)
              /* .setHttpProxyPool(httpProxyPool)
                .setProxyReuseInterval(500)*/
                .setTimeOut(100000)
                .setRetryTimes(3)
                .setDomain("shjiapai.cn")
                .setRetrySleepTime(5000);
    }
    public static void main(String[] args) {
        Spider spider = new Spider(new Jiapai());
        spider.addUrl("http://www.shjiapai.cn/Fyzs/index/qyid/"+m+"/p/1.html");
        spider.thread(5);
        spider.start();
    }

}

