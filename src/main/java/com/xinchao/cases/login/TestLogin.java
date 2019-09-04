package com.xinchao.cases.login;

import com.xinchao.core.validation.ResponseChecker;
import com.xinchao.core.HttpRequest;
import com.xinchao.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLogin {
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
        String url = baseUrl + "/SMSLogin?code=111111&source=1&tel=15844444444";
        HttpResponse response = new HttpRequest(url).doGet();
        System.out.println(response);

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
//        System.out.println(response.getEntity().getContent());
    }

    @Test
    public void testLogout() throws IOException {
        String url = baseUrl + "/logout";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
        System.out.println(response.getEntity().getContent());
    }
}
