package crawler.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/30.
 */
public class HttpClient {
    private static final String USERAGENT_CHROME = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
    public  static  String  doGet(String url,String charset){
        CloseableHttpClient httpClient= HttpClientBuilder.create().
                setProxy(new HttpHost("192.168.0.102",3234)).
                build();
        HttpGet httpGet=new HttpGet(url);
        String result = null;
        HttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                result = EntityUtils.toString(response.getEntity(), charset);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPost(String url, List<NameValuePair> params, String charset){
        CloseableHttpClient httpClient= HttpClientBuilder.create().
                setProxy(new HttpHost("192.168.0.102",3234)).
                setUserAgent(USERAGENT_CHROME).
                build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setRedirectsEnabled(true)
                .setCircularRedirectsAllowed(true)
                .setRelativeRedirectsAllowed(true)
                .setMaxRedirects(50)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(2000)
                .setSocketTimeout(50000).build();
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            HttpEntity entity = new UrlEncodedFormEntity(params, charset);
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
            if(httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    public static String postWithBody(String url, JSONObject reqBody, Map<String, String> headers, String charset) {
        String rt=null;
        try {
            CloseableHttpClient httpClient= HttpClientBuilder.create().
                    setProxy(new HttpHost("192.168.0.102",3234)).
                    setUserAgent(USERAGENT_CHROME).
                    build();
            StringEntity entity = new StringEntity(reqBody.toString(), charset);
            entity.setContentEncoding(charset);
            entity.setContentType("application/json");
            HttpPost httpPost = new HttpPost(url);
            if(headers != null){
                for(Map.Entry<String,String> header : headers.entrySet()){
                    httpPost.addHeader(header.getKey(), header.getValue());
                }
            }
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            rt = EntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
        }
        return rt;
    }

    public static  List<NameValuePair> getListNameValuePairs(Map<String,String> map){
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        for(String o:map.keySet()){
            list.add(new BasicNameValuePair(o,map.get(o)));
        }
        return  list;
    }
}
