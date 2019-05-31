package com.shawn.api.test.industryTestSuite;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.apitest02.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestIndustry {
    private String baseUrl = "https://life-circle-prd.xinchao.com/life/industry";
    @Test
    public void testGetIndustryLevelOne() throws IOException {
        String url = baseUrl + "/level/1";
        System.out.println(url);
        HttpResponse response = new HttpRequest(url).doGet();

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
        String url = baseUrl + "/level/2";
        HttpResponse response = new HttpRequest(url).doGet();

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
        String url = baseUrl + "/level/list";
        HttpResponse response = new HttpRequest(url).doGet();

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
        String url = baseUrl + "/level/shqlist";
        HttpResponse response = new HttpRequest(url).doGet();

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
        String url = baseUrl + "/parent/H00001";
        HttpResponse response = new HttpRequest(url).doGet();

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
