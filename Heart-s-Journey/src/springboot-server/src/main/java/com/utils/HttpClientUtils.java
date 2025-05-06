package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具类
 * 提供了HTTP GET和POST请求的封装方法
 */
public class HttpClientUtils {

    /**
     * 发送GET请求
     *
     * @param uri 请求的URI
     * @return 请求响应内容
     */
    public static String doGet(String uri) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(uri); // 将字符串转换为URL对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 打开连接
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 读取响应
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n"); // 读取每行数据并追加换行符
            }
            in.close(); // 关闭流
            return result.toString(); // 返回结果
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            return null; // 出现异常返回null
        }
    }

    /**
     * 发送POST请求
     *
     * @param url 请求的URL
     * @param param 请求参数
     * @return 请求响应内容
     */
    public static String doPost(String url, Map<String, String> param) {
        // 使用HttpClients类创建CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url); // 创建HttpPost对象
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key))); // 将参数转换为NameValuePair列表
                }
                // 设置请求体为UrlEncodedFormEntity对象
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity); // 设置Http Post请求的实体
            }
            // 执行请求并获得响应结果
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8"); // 将响应内容转换为字符串
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
        } finally {
            try {
                if (response != null) {
                    response.close(); // 关闭响应对象
                }
            } catch (IOException e) {
                e.printStackTrace(); // 打印IO异常信息
            }
        }
        return resultString; // 返回响应字符串
    }
}