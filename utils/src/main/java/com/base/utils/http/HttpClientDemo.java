package com.base.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description httpclient工具
 * @Author leihaoyuan
 * @Date 2020/4/27 9:10
 */
public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        doGet();
    }

    /**
     * Get 请求不带参数
     *
     * @throws Exception
     */
    public static void doGet() throws Exception {
        //常见一个 HttpClient 对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建 Get 请求对象。在请求中输入 url
        HttpGet get = new HttpGet("http://www.baidu.com");
        //发送请求，并返回响应
        CloseableHttpResponse res = client.execute(get);
        //处理响应
        //获取响应的状态码
        int code = res.getStatusLine().getStatusCode();
        System.out.println(code);
        //获取响应的内容
        HttpEntity entity = res.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println(content);
        //关闭连接
        client.close();
    }

    /**
     * Get 请求带参数
     * @throws Exception
     */
    public static void doGetParam() throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个封装 URI 的对象。在该对象中可以给定请求参数
        URIBuilder bui = new URIBuilder("https://www.sogou.com/web");
        bui.addParameter("query", "西游记");
        //创建一个 Get 请求对象
        HttpGet get = new HttpGet(bui.build());
        //发送请求，并返回响应
        CloseableHttpResponse res = client.execute(get);
        //处理响应
        //获取响应的状态码
        int code = res.getStatusLine().getStatusCode();
        System.out.println(code);
        //获取响应的内容
        HttpEntity entity = res.getEntity();
        String content = EntityUtils.toString(entity,"utf-8");
        System.out.println(content);
        //关闭连接
        client.close();
    }

    /**
     * 发送 POST 请求不带参数
     */
    public static void doPostTest() throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/test/post");
        CloseableHttpResponse res = client.execute(post);
        //处理响应
        //获取响应的状态码
        int code = res.getStatusLine().getStatusCode();
        System.out.println(code);
        //获取响应的内容
        HttpEntity entity = res.getEntity();
        String content = EntityUtils.toString(entity,"utf-8");
        System.out.println(content);
        //关闭连接
        client.close();
    }

    /**
     * 发送 POST 请求带参数
     */
    public static void doPostParamTest()throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/test/post/param");
        //给定参数
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name", "张三丰"));
        list.add(new BasicNameValuePair("pwd", "zhangsanfeng"));
        //将参数做字符串的转换
        StringEntity entity = new UrlEncodedFormEntity(list,"utf-8");
        //向请求中绑定参数
        post.setEntity(entity);
        //处理响应
        CloseableHttpResponse res = client.execute(post);
        //获取响应的状态码
        int code = res.getStatusLine().getStatusCode();
        System.out.println(code);
        //获取响应的内容
        HttpEntity en = res.getEntity();
        String content = EntityUtils.toString(en,"utf-8");
        System.out.println(content);
        //关闭连接
        client.close();
    }

    /**
     * 发送 POST 请求带 JSON 格式参数
     */
    public static void doPostParamJsonTest()throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/test/post/param/json");
        String json ="{'name':'张三丰','pwd':'zhangsanfeng'}";
        StringEntity entity = new StringEntity(json,ContentType.APPLICATION_JSON);
        //向请求中绑定参数
        post.setEntity(entity);
        //处理响应
        CloseableHttpResponse res = client.execute(post);
        //获取响应的状态码
        int code = res.getStatusLine().getStatusCode();
        System.out.println(code);
        //获取响应的内容
        HttpEntity en = res.getEntity();
        String content = EntityUtils.toString(en,"utf-8");
        System.out.println(content);
        //关闭连接
        client.close();
    }








}
