package com.xinchao.cases.industryTestSuite;

import com.xinchao.core.validation.ResponseChecker;
import com.xinchao.core.HttpRequest;
import com.xinchao.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestIndustry {
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
    public void testGetIndustryLevelOne() throws IOException {
        String url = baseUrl + "/industry/level/1";

        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");
		exceptMap.put("data[0].level",1);
		exceptMap.put("data[0].name", "医疗服务(整形、生活美容、专科医院下");
		exceptMap.put("data[0].industryId", "H00001");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testGetIndustryLevelTwo() throws IOException {
        String url = baseUrl + "/industry/level/2";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");
        exceptMap.put("data[0].level",2);
        exceptMap.put("data[0].name", "禁忌行业");
        exceptMap.put("data[0].industryId", "taboo_industry");
        exceptMap.put("data[0].parentId","0");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testGetIndustryLevelList() throws IOException {
        String url = baseUrl + "/industry/level/list";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");
        exceptMap.put("data[0].industryId","H00001");
        exceptMap.put("data[0].name", "医疗服务(整形、生活美容、专科医院下");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testGetIndustryLevelSHQList() throws IOException {
        String url = baseUrl + "/industry/level/shqlist";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");
        //exceptMap.put("data[0].industryId","H01701");
        exceptMap.put("data[0].name", "餐饮行业");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testGetIndustryLevelParent() throws IOException {
        String url = baseUrl + "/industry/parent/H00001";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");
        exceptMap.put("data[0].industryId","H01701");
        exceptMap.put("data[0].name", "医美整形");
        exceptMap.put("data[0].level", 2);

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
