package com.chenxianyong.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/24.
 */
public class MapUtilTest {
    @Test
    public void reversalMapTest() throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put("a","1");
        map.put("b","2");
        map.put("c","1");
        map.put("d","2");
        map.put("e","3");
        System.out.println(JSON.toJSON(map));
        Map<String, List<String>> listMap = MapUtil.reversalMap(map);
        System.out.println(JSON.toJSON(listMap));
    }

}