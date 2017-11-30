package crawler.utils;

import com.google.common.collect.ImmutableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by linxsh on 2017/5/9.
 */
public class RequestUtil {
    private static final String USERAGENT_CHROME = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
    static  List<String[]> httpProxyPool = ImmutableList.of(
            new String[]{"192.168.0.90", "4234"},
            new String[]{"192.168.0.93", "4234"},
            new String[]{"192.168.0.94", "4234"},
            new String[]{"192.168.0.96", "4234"},
            new String[]{"192.168.0.98", "4234"},
            new String[]{"192.168.0.99", "4234"},
            new String[]{"192.168.0.100", "4234"},
            new String[]{"192.168.0.101", "4234"},
            new String[]{"192.168.0.102", "4234"},
            new String[]{"192.168.0.103", "4234"}
    );

   public static void main(String[] args) {
        String result = doGet("http://www.fangjia.com");
        System.out.println(result);
    }
    /**
     * ∑¢ÀÕget«Î«Û
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, "gbk");
    }
    public static String doPost(String url, List<NameValuePair> params) {
        return doPost(url, params, "utf-8");
    }

    public static String doGet(String url, String charset) {
        String[] array=httpProxyPool.get((int)(0+Math.random()*(9)));
        CloseableHttpClient client = HttpClients
                .custom()
               //  .setProxy(new HttpHost(array[0],Integer.parseInt(array[1])))
                .setUserAgent(USERAGENT_CHROME)
                .build();

        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            //System.out.println(response.getStatusLine() + "\t" + url);
            if(statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
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
            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }
    public static String doPost(String url, List<NameValuePair> params, String charset) {

        CloseableHttpClient client = HttpClients
                .custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .setProxy(new HttpHost("192.168.0.103", 3234))
                .setUserAgent(USERAGENT_CHROME)
                .build();

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
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(entity);
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            //System.out.println(response.getStatusLine() + "\t" + url);
            if(statusCode == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
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
            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

}
