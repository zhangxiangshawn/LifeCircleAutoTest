package com.shawn.api.test.transfer;

import com.shawn.api.HttpRequest;
import com.shawn.api.validation.ResponseChecker;
import com.shawn.utils.GetTokenFromYml;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestTransfer {

    private String baseUrl = "https://life-circle-t.xinchao.com/life";
    private Map<String, Object> header;
    private static String token = GetTokenFromYml.getToken();

    public static Map<String, Object> setToken() {
        Map header = new HashMap();
        header.put("token", token);
        header.put("Content-Type", "application/json");
        return header;
    }

    @Test
    public void testGetTransferInfo() throws IOException {
        String url = baseUrl + "/transfer/201";

        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url).setHeaders(header).doGet();

        Map<String, Object> exceptMap = new HashMap<String, Object>();

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }

    @Test
    public void testPostTransfer() throws IOException {
        String url = baseUrl + "/transfer/";
        //header
        Map<String, Object> header = setToken();
        HttpResponse response = new HttpRequest(url)
                .setHeaders(header)
                .setContent("{\"amount\":\"3333\", \"receiptUrl\":\"https://huawei-obs-life-circle.obs.cn-east-2.myhuaweicloud.com/ffec8bcc14e34f4aaa5079850f130ecc.jpg\"}")
                .doPost();

        Map<String, Object> exceptMap = new HashMap<String, Object>();

        exceptMap.put("status.message", "成功");

        ResponseChecker responseChecker = new ResponseChecker(response);
        responseChecker.codeCheck(200);
        responseChecker.dataCheck(exceptMap);
    }
}
