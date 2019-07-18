package com.shawn.api;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 2019/2/26.
 * Desc: 接口自动化测试http框架封装
 * 
 */
public class HttpRequest {

    private String url;
    private Map<String,Object> headers;
    private Map<String,Object> params;
    private String content;

    //设置url
    public HttpRequest(String url){
        this.url =url;
    }

    //设置header，可选
    public HttpRequest setHeaders(Map<String,Object> headers){
        this.headers = headers;
        return this;
    }

    //设置请求参数，可选
    public HttpRequest setParams(Map<String,Object> params){
        this.params = params;
        return this;
    }

    //设置请求体
    public HttpRequest setContent(String content){
        this.content = content;
        return this;
    }

    //Get请求方法
    public HttpResponse doGet(){
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            if(headers != null && headers.size() > 0){
                for(Map.Entry<String,Object> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), String.valueOf(header.getValue()));
                }
            }

            if(params != null && params.size() > 0){
                StringBuilder sb = new StringBuilder();
                if(! url.contains("?")){
                    sb.append("?");
                }
                for(Map.Entry<String,Object> param : params.entrySet()) {
                    sb.append(param.getKey() + "=" + param.getValue() + "&");
                }
                sb.deleteCharAt(sb.lastIndexOf("&"));
                url = url + sb.toString();
            }
            HttpResponse response = client.execute(request);
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public HttpResponse doPost(){
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpPost request = new HttpPost(url);

            //设置url
            request.setURI(new URI(url));

            if(headers != null && headers.size() > 0){
                for(Map.Entry<String,Object> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), String.valueOf(header.getValue()));
                }
            }

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            //设置参数
            if(params != null && params.size() > 0){
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    nvps.add(new BasicNameValuePair(name, value));
                }
                request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            }
            //设置body内容
            if(content != null){
                request.setEntity(new StringEntity(content));
            }

            HttpResponse response = client.execute(request);
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public HttpResponse doPut(){
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpPut request = new HttpPut(url);

            //设置url
            request.setURI(new URI(url));

            if(headers != null && headers.size() > 0){
                for(Map.Entry<String,Object> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), String.valueOf(header.getValue()));
                }
            }

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            //设置参数
            if(params != null && params.size() > 0){
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    nvps.add(new BasicNameValuePair(name, value));
                }
                request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            }
            //设置body内容
            if(content != null){
                request.setEntity(new StringEntity(content));
            }

            HttpResponse response = client.execute(request);
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public HttpResponse doDelete(){
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpDelete request = new HttpDelete(url);
            if(headers != null && headers.size() > 0){
                for(Map.Entry<String,Object> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), String.valueOf(header.getValue()));
                }
            }

            if(params != null && params.size() > 0){
                StringBuilder sb = new StringBuilder();
                if(! url.contains("?")){
                    sb.append("?");
                }
                for(Map.Entry<String,Object> param : params.entrySet()) {
                    sb.append(param.getKey() + "=" + param.getValue() + "&");
                }
                sb.deleteCharAt(sb.lastIndexOf("&"));
                url = url + sb.toString();
            }
            HttpResponse response = client.execute(request);
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

}
