package com.xinchao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class ResponseChecker {
    private static Logger logger = (Logger) Logger.getLogger(String.valueOf(ResponseChecker.class));

    private HttpResponse response;
    public ResponseChecker (HttpResponse response){
        this.response = response;
    }

    public boolean codeCheck(int code){
        if(response == null){
            return false;
        }
        try {
            Assert.assertEquals(code, response.getStatusLine().getStatusCode());
            return true;
        }catch (Error e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean dataCheck(Map<String,Object> exceptMap) throws IOException {
        if(response == null || exceptMap == null){
            return false;
        }

        String content = EntityUtils.toString(response.getEntity());
        logger.info("response: " + content);

        Map<String,Object> actualMap = parse(content, null);

        boolean flag = true;
        for(Map.Entry<String, Object> item : exceptMap.entrySet()){
            logger.info(item.getKey() + " 【expected】: " + item.getValue() + "，and【actual】：" + actualMap.get(item.getKey()));
            try {
                Assert.assertEquals(item.getValue(), actualMap.get(item.getKey()));
            }catch (Error e){
                flag = false;
                e.printStackTrace();
            }
        }

        Assert.assertEquals(true,flag);//最后false就中断本次测试
        return flag;
    }

    /**
     * @author xinchao
     * @param json 待解析的json
     * @param parent 父key
     * @return 返回一个map，key按json的路径层次存储
     */
    public static Map<String, Object> parse(Object json, String parent) {
        String jsonStr = String.valueOf(json);
        if (json == null || "".equals(jsonStr)) {
            System.err.println("parse exception, json is null.");
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        if (jsonStr.startsWith("{") && jsonStr.endsWith("}")) {//jsonObject
            JSONObject obj = JSON.parseObject(jsonStr);
            Set<Map.Entry<String, Object>> items = obj.entrySet();
            if (items == null || items.size() < 1) {
                System.err.println("parse exception, json object is null.");
                return map;
            }
            parent = parent == null || "".equals(parent) ? "" : parent + ".";
            for (Map.Entry<String, Object> item : items) {
                //System.out.println(parent + item.getKey() + ":" + item.getValue());
                Map<String, Object> temp = parse(item.getValue(), parent + item.getKey());
                if (temp != null && temp.size() > 0) {
                    map.putAll(temp);
                }
            }

        } else if (jsonStr.startsWith("[") && jsonStr.endsWith("]")) {//jsonArray
            JSONArray array = JSON.parseArray(jsonStr);
            if (array == null || array.size() < 1) {
                System.err.println("parse exception, json array is null.");
                return map;
            }
            int index = 0;
            String tempParent = "";
            for (Object child : array.toArray()) {
                tempParent = parent == null || "".equals(parent) ? "" : parent + "[" + index + "]";
                Map<String, Object> temp = parse(child, tempParent);
                if (temp != null && temp.size() > 0) {
                    map.putAll(temp);
                }
                index++;
            }

        } else {//unknown or item
            if (parent != null) {//item
                map.put(parent, json);
            }else{//unknown
                map.put("", json);
            }
        }
        return map;
    }
}
