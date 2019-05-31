package com.shawn.api.test.login;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.apitest02.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLogin {
    private String baseUrl = "https://life-circle-prd.xinchao.com/life";
    @Test
    public void testGetSMSCode() throws IOException {
        String url = baseUrl + "/SMSCode/get";
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("Content-Type","application/json");
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"codeType\": \"loginSms\", \"tel\": \"17313090076\"}")
                .doPost();
        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testSMSLogin() throws IOException {
        String url = baseUrl + "/SMSLogin?code=243504&source=1&tel=17313090076";
        HttpResponse response = new HttpRequest(url).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }
}
