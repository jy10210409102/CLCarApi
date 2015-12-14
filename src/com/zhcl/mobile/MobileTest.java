/**
 * 
 */
package com.zhcl.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.zhcl.utils.log.L;

/**
 * @ClassName: MobileTest
 * @author zhonghong.chenli
 * @date 2015年12月14日 下午7:02:24
 */
public class MobileTest {
	private static final String tag = MobileTest.class.getSimpleName();

	public static void main(String[] args) throws IOException {
		L.i(tag, "main");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "4028a08151a118420151a11899e90000");
		final String KEY = "4028a08151a118420151a11899e90000";
		for(int i = 0; i < 20; i++){
			long currentTime = System.currentTimeMillis();
			//http://www.wearefamily.link/carapi/api-json/requesttime
			//当前架构请求，get方式比 post方式稍耗时一点。
			L.i(tag, requestByPostMethod("http://localhost:8080/carapi/api-json/requesttime", KEY, map));
			L.i(tag, "请求一次消耗时间：" + (System.currentTimeMillis() - currentTime));
		}
	}

	/**
	 * 通过GET方式发起http请求
	 */
	public static String requestByGetMethod(String url) {
		// 创建默认的httpClient实例
		String result = null;
		CloseableHttpClient httpClient = getHttpClient();
		try {
			// 用get方法发送http请求
			HttpGet get = new HttpGet(url);
			L.i(tag, "执行get请求:...." + get.getURI());
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			try {
				// response实体
//				HttpEntity entity = httpResponse.getEntity();
//				if (null != entity) {
//					L.i(tag, "响应状态码:" + httpResponse.getStatusLine());
//					L.i(tag, "-------------------------------------------------");
//					L.i(tag, "响应内容:" + EntityUtils.toString(entity));
//					L.i(tag, "-------------------------------------------------");
//				}
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
					L.e(tag, "成功！！");
	                // 2、获取response的entity。  
	                HttpEntity entity = httpResponse.getEntity();  
	                result = EntityUtils.toString(entity, "UTF-8");
		        }  
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * POST方式发起http请求
	 * httpClient 不新建则可以保存session
	 */
	public static String requestByPostMethod(String url, String keyCode, HashMap<String, String> params) {
		String result = null;
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpPost post = new HttpPost(url); // 这里用上本机的某个工程做测试
			post.addHeader("key", keyCode);
			if(params != null){
				// 创建参数列表
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				// url格式编码
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
				post.setEntity(uefEntity);
			}
			L.i(tag, "POST 请求...." + post.getURI());
			
			

			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
				L.e(tag, "成功！！");
                // 2、获取response的entity。  
                HttpEntity entity = httpResponse.getEntity();  
                result = EntityUtils.toString(entity, "UTF-8");
	        }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}
	
	  
    public static void downloadPagebyGetMethod(String url) throws IOException {  
  
        // 1、通过HttpGet获取到response对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        HttpGet httpGet = new HttpGet(url);  
        CloseableHttpResponse response = httpClient.execute(httpGet);  
  
        InputStream is = null;  
        Scanner sc = null;  
        Writer os = null;  
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
            try {  
                // 2、获取response的entity。  
                HttpEntity entity = response.getEntity();  
  
                // 3、获取到InputStream对象，并对内容进行处理  
                is = entity.getContent();  
                sc = new Scanner(is);  
                // String filename = path.substring(path.lastIndexOf('/')+1);  
                String filename = "2.txt";  
                os = new PrintWriter(filename);  
                while (sc.hasNext()) {  
                    os.write(sc.nextLine());  
                }  
  
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
            } finally {  
                if (sc != null) {  
                    sc.close();  
                }  
                if (is != null) {  
                    is.close();  
                }  
                if (os != null) {  
                    os.close();  
                }  
                if (response != null) {  
                    response.close();  
                }  
            }  
        }  
  
    }  
	
}
