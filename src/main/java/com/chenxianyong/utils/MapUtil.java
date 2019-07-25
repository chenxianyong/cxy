package com.chenxianyong.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenXianyong
 * @data 2019年7月19日 下午2:11:46
 * @description 处理Map的工具类
 */
public class MapUtil {

	/**
	 * @author ChenXianyong
	 * @description 反转map，将入参map的key转成新map的value，将入参map的value转成新map的key。
	 *              考虑到value可能相同，所以返回的新map中的value是一个List。
	 * @data 2019年7月19日
	 * @param map
	 * @return Map<String, List<String>>
	 */
	public static Map<String, List<String>> reversalMap(Map<String, String> map) {
		return map.entrySet().stream().collect(
				Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
	}
}
