package com.shawn.api.test.media;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.apitest02.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestMedia {
    private String baseUrl = "https://test-api-life.xinchao.mobi/life";

    @Test
    public void testGetMediaList() throws IOException {
        String url = baseUrl + "/media/list";
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Content-Type","application/json");
        header.put("token","eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTcyNTU3MDksInVzZXJuYW1lIjoiOTBhYzg5MmItODU0ZS00YmQ4LWE4MWYtZTliYTU2NTdkOTU1In0.T-hlezJVvjniroQ6FD0KONBjJWwvGpcOOZKUgh6hiTIJy3mXnklzH6yBXhmF0j6MQ9LUYZJkMtnuDu1F0pbHuQ");
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
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Content-Type","application/json");
        header.put("token","eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTcyNzg5MjIsInVzZXJuYW1lIjoiYzJiNzk0MDMtODkzMi00NzNmLWE1YTMtYWMwZTIyOTM3NzUyIn0.O4wD2lbq8DETtQYPHHENVTZHO1m9wV1LHgprbvqhV1OhA41zalDwIaOiEvZjFEYj5zt5isWAw_ZAccU-abeYSA");
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
