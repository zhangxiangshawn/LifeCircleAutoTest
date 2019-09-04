package com.xinchao.utils;

import com.aventstack.extentreports.model.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

public class GetTokenFromYml {
    private static String token;

    public static String getToken(){
        try {
        Yaml yaml = new Yaml();
        URL url = Test.class.getClassLoader().getResource("application.yml");
        if (url != null) {
//            获取application.yml文件中的配置数据，然后转换为obj，
            Object obj =yaml.load(new FileInputStream(url.getFile()));
            System.out.println(obj);
            obj.getClass().getName();
//            也可以将值转换为Map
            Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
            for (Object key : map.keySet()) {
                token = map.get(key).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
