package crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.ImmutableList;
import crawler.utils.RequestUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
public class FangGuGu implements PageProcessor {
    String cities = "{\"first_letter_list\":[\"A\",\"F\",\"G\",\"H\",\"J\",\"L\",\"N\",\"Q\",\"S\",\"X\",\"Y\",\"Z\"],\"data\":[{\"first_letter\":\"A\",\"data_obj_list\"\n" +
            ":[{\"province\":\"安徽\",\"cities_list\":[\"安庆\",\"蚌埠\",\"滁州\",\"合肥\",\"六安\",\"马鞍山\",\"宿州\",\"芜湖\"]}]},{\"first_letter\":\"F\",\"data_obj_list\"\n" +
            ":[{\"province\":\"福建\",\"cities_list\":[\"福州\",\"龙岩\",\"宁德\",\"莆田\",\"泉州\",\"厦门\",\"漳州\"]}]},{\"first_letter\":\"G\",\"data_obj_list\"\n" +
            ":[{\"province\":\"甘肃\",\"cities_list\":[\"兰州\"]},{\"province\":\"广东\",\"cities_list\":[\"东莞\",\"佛山\",\"广州\",\"惠州\",\"江门\",\"清" +
            "远\",\"汕头\",\"深圳\",\"湛江\",\"肇庆\",\"中山\",\"珠海\"]},{\"province\":\"广西\",\"cities_list\":[\"北海\",\"桂林\",\"柳州\",\"南宁\",\"钦州\"]},{\"province\"\n" +
            ":\"贵州\",\"cities_list\":[\"贵阳\",\"六盘水\",\"遵义\"]}]},{\"first_letter\":\"H\",\"data_obj_list\":[{\"province\":\"海南\",\"cities_list\"\n" +
            ":[\"海口\",\"三亚\"]},{\"province\":\"河北\",\"cities_list\":[\"保定\",\"沧州\",\"承德\",\"邯郸\",\"廊坊\",\"秦皇岛\",\"石家庄\",\"唐山\"]},{\"province\"\n" +
            ":\"河南\",\"cities_list\":[\"安阳\",\"鹤壁\",\"焦作\",\"开封\",\"洛阳\",\"漯河\",\"南阳\",\"平顶山\",\"濮阳\",\"三门峡\",\"商丘\",\"新乡\",\"信阳\",\"许昌\",\"郑州\",\"周" +
            "口\",\"驻马店\"]},{\"province\":\"黑龙江\",\"cities_list\":[\"大庆\",\"哈尔滨\",\"牡丹江\"]},{\"province\":\"湖北\",\"cities_list\":[\"鄂州\",\"" +
            "黄石\",\"十堰\",\"武汉\",\"襄阳\",\"宜昌\"]},{\"province\":\"湖南\",\"cities_list\":[\"长沙\",\"株洲\"]}]},{\"first_letter\":\"J\",\"data_obj_list\"\n" +
            ":[{\"province\":\"吉林\",\"cities_list\":[\"长春\",\"吉林\",\"松原\",\"延边\"]},{\"province\":\"江苏\",\"cities_list\":[\"常州\",\"昆山\",\"连" +
            "云港\",\"南京\",\"南通\",\"苏州\",\"宿迁\",\"泰州\",\"无锡\",\"徐州\",\"盐城\",\"扬州\",\"镇江\"]},{\"province\":\"江西\",\"cities_list\":[\"赣州\",\"九江\",\"南" +
            "昌\",\"萍乡\"]}]},{\"first_letter\":\"L\",\"data_obj_list\":[{\"province\":\"辽宁\",\"cities_list\":[\"鞍山\",\"大连\",\"丹东\",\"抚顺\"\n" +
            ",\"葫芦岛\",\"锦州\",\"盘锦\",\"沈阳\",\"营口\"]}]},{\"first_letter\":\"N\",\"data_obj_list\":[{\"province\":\"内蒙古\",\"cities_list\":\n" +
            "[\"巴彦淖尔\",\"包头\",\"赤峰\",\"鄂尔多斯\",\"呼和浩特\",\"呼伦贝尔\",\"通辽\",\"乌兰察布\",\"锡林浩特\",\"兴安盟\"]},{\"province\":\"宁夏\",\"cities_list\":[\"银" +
            "川\"]}]},{\"first_letter\":\"Q\",\"data_obj_list\":[{\"province\":\"青海\",\"cities_list\":[\"西宁\"]}]},{\"first_letter\"\n" +
            ":\"S\",\"data_obj_list\":[{\"province\":\"山东\",\"cities_list\":[\"滨州\",\"德州\",\"东营\",\"济南\",\"济宁\",\"临沂\",\"青岛\",\"日照\",\"泰安\",\"" +
            "威海\",\"潍坊\",\"烟台\",\"淄博\"]},{\"province\":\"山西\",\"cities_list\":[\"长治\",\"大同\",\"临汾\",\"吕梁\",\"太原\"]},{\"province\":\"陕西\",\"cities_list\"\n" +
            ":[\"宝鸡\",\"渭南\",\"西安\",\"咸阳\",\"榆林\"]},{\"province\":\"上海\",\"cities_list\":[\"上海\"]},{\"province\":\"四川\",\"cities_list\":[\"" +
            "成都\",\"达州\",\"广安\",\"泸州\",\"眉山\",\"南充\",\"遂宁\",\"宜宾\",\"资阳\"]}]},{\"first_letter\":\"X\",\"data_obj_list\":[{\"province\":\"西藏\"\n" +
            ",\"cities_list\":[\"拉萨\"]},{\"province\":\"新疆\",\"cities_list\":[\"乌鲁木齐\"]}]},{\"first_letter\":\"Y\",\"data_obj_list\"\n" +
            ":[{\"province\":\"云南\",\"cities_list\":[\"大理\",\"昆明\",\"曲靖\"]}]},{\"first_letter\":\"Z\",\"data_obj_list\":[{\"province\"\n" +
            ":\"浙江\",\"cities_list\":[\"杭州\",\"湖州\",\"嘉兴\",\"金华\",\"丽水\",\"宁波\",\"衢州\",\"绍兴\",\"台州\",\"温州\",\"舟山\"]}]}],\"commonly_used_cities\"\n" +
            ":[\"北京\",\"上海\",\"天津\",\"广州\",\"武汉\"],\"msg\":\"查询成功\",\"success\":true}";

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

    JSONObject cityJson = JSONObject.parseObject(cities);
    List list=new ArrayList();
    Object  array[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,
            37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,53,55,56,57,58,59,60,61,62,63,"a","b","c","d","e","f","g","h",
            "i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","居","号","苑"};
    public void process(Page page) {
        String url = page.getRequest().getUrl();
        if(url.matches("http://www.fungugu.com/JinRongGuZhi/toRapidValuation_noLogin\\?csmc=.+")){
            JSONArray jsonArray =cityJson.getJSONArray("data");
            for(Object letterObj: jsonArray) {
                JSONObject letterJo = (JSONObject)letterObj;
                JSONArray cityJa = letterJo.getJSONArray("data_obj_list");
                for(Object letter:cityJa){
                    JSONObject letterobj = (JSONObject)letterObj;
                    JSONArray cityJaq = letterobj.getJSONArray("data_obj_list");
                    for(Object let:cityJaq){
                        JSONObject lettero = (JSONObject)let;
                        JSONArray array=lettero.getJSONArray("cities_list");
                        for(Object o:array){
                            list.add(o);
                        }

                    }
                }
            }
            List newList=new ArrayList();
            for (Object i:list){
                if(!newList.contains(i)){
                    newList.add(i);
                }
            }

            for(Object city:newList){
                for(Object i:array){
                    String URL="http://www.fungugu.com/JinRongGuZhi/participleSearch_noLogin?q="+i+"&limit=150&timestamp=1494900125850&v="+i+"&city="+city;
                    String result1 = RequestUtil.doGet(URL);
                    JSONArray Array = JSONArray.parseArray(result1);
                    for(Object object:Array){
                        JSONObject jsonObject = (JSONObject)object;
                        String  address=jsonObject.get("similarAddress").toString();
                        String link="http://www.fungugu.com/JinRongGuZhi/rapidValuation_noLogin?city_name="+city+"&xq_area=100&xq_name="+address+"&xq_id=63";
                        //String result = RequestUtil.doPost(link);
                        // System.out.println(result);
                    }

                }
            }

        }
    }

    public Site getSite() {
        return Site.me()
                .setCharset("utf-8")
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0")
                .setCycleRetryTimes(3)
                .setHttpProxyPool(httpProxyPool)
                .setProxyReuseInterval(500)
//              .setHttpProxy(new HttpHost("192.168.0.96", 3234))
                .setRetryTimes(3)
                .setDomain("fungugu.com")
                .setRetrySleepTime(1000);
    }
    public static void main(String[] args) {
        Spider spider = new Spider(new FangGuGu());
        spider.addUrl("http://www.fungugu.com/JinRongGuZhi/toRapidValuation_noLogin?csmc=合肥");
        spider.thread(1);
        spider.start();

    }
}
