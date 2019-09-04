package com.xinchao.cases.media;

import com.xinchao.core.validation.ResponseChecker;
import com.xinchao.core.HttpRequest;
import com.xinchao.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestMedia {
    private String baseUrl = "https://life-circle-t.xinchao.com/life";
    private Map<String, Object> header;
    private static String token = GetTokenFromYml.getToken();

    public static Map<String, Object> setToken(){
        Map header = new HashMap();
        header.put("token", token);
        header.put("Content-Type","application/json");
        return header;
    }
    @Test
    public void testGetMediaList() throws IOException {
        String url = baseUrl + "/media/list";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{ \"pageVo\": { \"pageIndex\":1, \"pageSize\": 10 }, \"screenType\": 3}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testMediaCreativeUpload() throws IOException {
        String url = baseUrl + "/media/creativeUpload";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{ \"fileInfo\":  \"screenType\": 3}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testDefaultList() throws IOException {
        String url = baseUrl + "/media/default/list";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{ \"pageVo\": { \"pageIndex\":1, \"pageSize\": 10 }}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }


    @Test
    public void testDeleteMediaMaterial() throws IOException {
        String url = baseUrl + "/media/material/1";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doDelete();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }
}
