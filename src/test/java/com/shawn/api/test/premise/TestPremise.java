package com.shawn.api.test.premise;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.apitest02.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestPremise {
    private String baseUrl = "https://test-api-life.xinchao.mobi/life";

    @Test
    public void testGetPremiseList() throws IOException {
        String url = baseUrl + "/premise/list";
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Content-Type","application/json");
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{ \"city\": \"310100\", \"distance\": \"2000\", \"latitude\": \"31.227576\",\"longitude\": \"121.4698060759246\"}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPremiseDetail() throws IOException {
        String url = baseUrl + "/premise/863944";
        HttpResponse response = new HttpRequest(url).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }

    @Test
    public void testPremiseStatistic() throws IOException {
        String url = baseUrl + "/premise/statistic/863944";
        HttpResponse response = new HttpRequest(url).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }

    @Test
    public void testPremiseGetfilter() throws IOException {
        String url = baseUrl + "/premise/getfilter";
        HttpResponse response = new HttpRequest(url).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }
}
