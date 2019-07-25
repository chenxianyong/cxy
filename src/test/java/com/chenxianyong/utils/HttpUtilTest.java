package com.chenxianyong.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static com.chenxianyong.utils.HttpUtil.*;

/**
 * Created by Administrator on 2019/7/24.
 */
public class HttpUtilTest {
    public static final String hasParamsUrl = "http://47.94.231.5/api2/informationMessage/list.koala";
    public static final String noParamsUrl = "http://47.94.231.5/api2/fav/myFav.koala";
    @Test
    public void doGetNoParams() throws Exception {
        String s = doGet(noParamsUrl);
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void doGetHasParams() throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("cityId",201);
        String s = doGet(hasParamsUrl, params);
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void doPostNoParams() throws Exception {
        String s = doPost(noParamsUrl);
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void doPostHasParamsMap() throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("cityId",201);
        String s = doPost(hasParamsUrl, params);
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void doPostHasParamsJSON() throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("cityId",201);
        Object json = JSON.toJSON(params);
        String s = doPost(hasParamsUrl, json);
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void doPostSSL() throws Exception {
    }

    @Test
    public void doPostSSL1() throws Exception {
    }

}