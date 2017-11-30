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
            ":[{\"province\":\"����\",\"cities_list\":[\"����\",\"����\",\"����\",\"�Ϸ�\",\"����\",\"��ɽ\",\"����\",\"�ߺ�\"]}]},{\"first_letter\":\"F\",\"data_obj_list\"\n" +
            ":[{\"province\":\"����\",\"cities_list\":[\"����\",\"����\",\"����\",\"����\",\"Ȫ��\",\"����\",\"����\"]}]},{\"first_letter\":\"G\",\"data_obj_list\"\n" +
            ":[{\"province\":\"����\",\"cities_list\":[\"����\"]},{\"province\":\"�㶫\",\"cities_list\":[\"��ݸ\",\"��ɽ\",\"����\",\"����\",\"����\",\"��" +
            "Զ\",\"��ͷ\",\"����\",\"տ��\",\"����\",\"��ɽ\",\"�麣\"]},{\"province\":\"����\",\"cities_list\":[\"����\",\"����\",\"����\",\"����\",\"����\"]},{\"province\"\n" +
            ":\"����\",\"cities_list\":[\"����\",\"����ˮ\",\"����\"]}]},{\"first_letter\":\"H\",\"data_obj_list\":[{\"province\":\"����\",\"cities_list\"\n" +
            ":[\"����\",\"����\"]},{\"province\":\"�ӱ�\",\"cities_list\":[\"����\",\"����\",\"�е�\",\"����\",\"�ȷ�\",\"�ػʵ�\",\"ʯ��ׯ\",\"��ɽ\"]},{\"province\"\n" +
            ":\"����\",\"cities_list\":[\"����\",\"�ױ�\",\"����\",\"����\",\"����\",\"���\",\"����\",\"ƽ��ɽ\",\"���\",\"����Ͽ\",\"����\",\"����\",\"����\",\"���\",\"֣��\",\"��" +
            "��\",\"פ���\"]},{\"province\":\"������\",\"cities_list\":[\"����\",\"������\",\"ĵ����\"]},{\"province\":\"����\",\"cities_list\":[\"����\",\"" +
            "��ʯ\",\"ʮ��\",\"�人\",\"����\",\"�˲�\"]},{\"province\":\"����\",\"cities_list\":[\"��ɳ\",\"����\"]}]},{\"first_letter\":\"J\",\"data_obj_list\"\n" +
            ":[{\"province\":\"����\",\"cities_list\":[\"����\",\"����\",\"��ԭ\",\"�ӱ�\"]},{\"province\":\"����\",\"cities_list\":[\"����\",\"��ɽ\",\"��" +
            "�Ƹ�\",\"�Ͼ�\",\"��ͨ\",\"����\",\"��Ǩ\",\"̩��\",\"����\",\"����\",\"�γ�\",\"����\",\"��\"]},{\"province\":\"����\",\"cities_list\":[\"����\",\"�Ž�\",\"��" +
            "��\",\"Ƽ��\"]}]},{\"first_letter\":\"L\",\"data_obj_list\":[{\"province\":\"����\",\"cities_list\":[\"��ɽ\",\"����\",\"����\",\"��˳\"\n" +
            ",\"��«��\",\"����\",\"�̽�\",\"����\",\"Ӫ��\"]}]},{\"first_letter\":\"N\",\"data_obj_list\":[{\"province\":\"���ɹ�\",\"cities_list\":\n" +
            "[\"�����׶�\",\"��ͷ\",\"���\",\"������˹\",\"���ͺ���\",\"���ױ���\",\"ͨ��\",\"�����첼\",\"���ֺ���\",\"�˰���\"]},{\"province\":\"����\",\"cities_list\":[\"��" +
            "��\"]}]},{\"first_letter\":\"Q\",\"data_obj_list\":[{\"province\":\"�ຣ\",\"cities_list\":[\"����\"]}]},{\"first_letter\"\n" +
            ":\"S\",\"data_obj_list\":[{\"province\":\"ɽ��\",\"cities_list\":[\"����\",\"����\",\"��Ӫ\",\"����\",\"����\",\"����\",\"�ൺ\",\"����\",\"̩��\",\"" +
            "����\",\"Ϋ��\",\"��̨\",\"�Ͳ�\"]},{\"province\":\"ɽ��\",\"cities_list\":[\"����\",\"��ͬ\",\"�ٷ�\",\"����\",\"̫ԭ\"]},{\"province\":\"����\",\"cities_list\"\n" +
            ":[\"����\",\"μ��\",\"����\",\"����\",\"����\"]},{\"province\":\"�Ϻ�\",\"cities_list\":[\"�Ϻ�\"]},{\"province\":\"�Ĵ�\",\"cities_list\":[\"" +
            "�ɶ�\",\"����\",\"�㰲\",\"����\",\"üɽ\",\"�ϳ�\",\"����\",\"�˱�\",\"����\"]}]},{\"first_letter\":\"X\",\"data_obj_list\":[{\"province\":\"����\"\n" +
            ",\"cities_list\":[\"����\"]},{\"province\":\"�½�\",\"cities_list\":[\"��³ľ��\"]}]},{\"first_letter\":\"Y\",\"data_obj_list\"\n" +
            ":[{\"province\":\"����\",\"cities_list\":[\"����\",\"����\",\"����\"]}]},{\"first_letter\":\"Z\",\"data_obj_list\":[{\"province\"\n" +
            ":\"�㽭\",\"cities_list\":[\"����\",\"����\",\"����\",\"��\",\"��ˮ\",\"����\",\"����\",\"����\",\"̨��\",\"����\",\"��ɽ\"]}]}],\"commonly_used_cities\"\n" +
            ":[\"����\",\"�Ϻ�\",\"���\",\"����\",\"�人\"],\"msg\":\"��ѯ�ɹ�\",\"success\":true}";

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
            "i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","��","��","Է"};
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
        spider.addUrl("http://www.fungugu.com/JinRongGuZhi/toRapidValuation_noLogin?csmc=�Ϸ�");
        spider.thread(1);
        spider.start();

    }
}
