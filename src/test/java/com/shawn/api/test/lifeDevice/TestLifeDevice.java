package com.shawn.api.test.lifeDevice;

import com.shawn.api.validation.ResponseChecker;
import com.shawn.api.HttpRequest;
import com.shawn.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLifeDevice {
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
    public void testGetLifeDevice() throws IOException {
        String url = baseUrl + "/device/price?cityCode=310100";
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String,Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
