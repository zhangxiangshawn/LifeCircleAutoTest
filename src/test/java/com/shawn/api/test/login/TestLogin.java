package com.shawn.api.test.login;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.apitest02.HttpRequest;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLogin {
    private String baseUrl = "http://localhost:9309/life";
    @Test
    public void testCheckSms() throws IOException {
        String url = baseUrl + "/checkSms";
        HttpResponse response = new HttpRequest(url)
                .setContent( "{\"amount\":\"0\", \"orderNumber\":\"201903120001\", \"payImmediate\":\"0\"}")
                .doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
