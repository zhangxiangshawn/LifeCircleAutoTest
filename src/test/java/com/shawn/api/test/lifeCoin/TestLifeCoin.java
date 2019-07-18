package com.shawn.api.test.lifeCoin;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.api.HttpRequest;
import com.shawn.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLifeCoin {
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
    public void testGetCoinBalance() throws IOException {
        String url = baseUrl + "/coin/balance";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

//    @Test
//    public void testGetCoinBonus() throws IOException {
//        String url = baseUrl + "/coin/bonus";
//        Map<String, Object> header = setToken();
//        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();
//
//        Map<String, Object> exceptMap = new HashMap<String,Object>();
//
//        exceptMap.put("status.message", "成功");
//        //exceptMap.put("data.minAmount",100);
//        exceptMap.put("data.basePercentage",0);
//
//        ResponseChecker responseChecker = new ResponseChecker(response);
//        responseChecker.codeCheck(200);
//        responseChecker.dataCheck(exceptMap);
//    }

    @Test
    public void testPostCoinConsume() throws IOException {
        String url = "https://life-circle-t.xinchao.com/life/coin/consume";

        //header
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"amount\":\"480\", \"orderNumber\":\"ZT2019071000000003test\", \"payImmediate\":\"0\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostCoinExpense() throws IOException {
        String url = baseUrl + "/coin/expense";

        //header
        Map<String, Object> header = setToken();

        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"amount\":\"0\", \"expenseType\":\"0\", \"pageIndex\":\"1\", \"pageSize\":\"10\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostCoinRecharge() throws IOException {
        String url = baseUrl + "/coin/recharge";

        //header
        Map<String, Object> header = setToken();

        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"amount\":\"11.00\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostCoinRefund() throws IOException {
        String url = baseUrl + "/coin/refund";

        //header
        Map<String, Object> header = setToken();

        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"amount\":\"1110\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
