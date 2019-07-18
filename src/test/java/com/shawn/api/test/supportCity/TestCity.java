package com.shawn.api.test.supportCity;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.api.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestCity {
    private String baseUrl = "life-circle-t.xinchao.com/life";
    @Test
    public void testGetCityList() throws IOException {
        String url = baseUrl + "/city/list";
        HttpResponse response = new HttpRequest(url).doGet();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testGetSingleCity() throws IOException {
        String url = baseUrl + "/city/";
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Content-Type","application/json");
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{ \"bdCityId\": \"257\"}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
