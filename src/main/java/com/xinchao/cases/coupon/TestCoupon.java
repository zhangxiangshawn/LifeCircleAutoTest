package com.xinchao.cases.coupon;

import com.xinchao.core.HttpRequest;
import com.xinchao.core.validation.ResponseChecker;
import com.xinchao.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestCoupon {

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
    public void testGetTotalCoupon() throws IOException {
        String url = baseUrl + "/coupon/entity/total";

        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostCouponList() throws IOException {
        String url = baseUrl + "/coupon/entity/list";
        //header
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"cityCode\":\"510100\", \"couponName\":\"小程序注册送券-陈卓\", \"couponType\":\"1\",\"pageIndex\":\"1\", \"pageSize\":\"1\",\"type\":\"1\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostCouponFirst() throws IOException {
        String url = baseUrl + "/coupon/entity/first";
        //header
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"cityCode\":\"510100\", \"couponName\":\"小程序注册送券-陈卓\", \"couponType\":\"0\",\"pageIndex\":\"0\", \"pageSize\":\"0\",\"type\":\"0\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
    @Test
    public void testPostCouponBest() throws IOException {
        String url = baseUrl + "/coupon/entity/best";
        //header
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"cityCode\":\"510100\", \"price\":\"1000\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }



}
