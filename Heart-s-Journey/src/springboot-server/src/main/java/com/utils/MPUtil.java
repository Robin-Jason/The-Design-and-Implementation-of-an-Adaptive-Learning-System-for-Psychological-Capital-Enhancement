package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * Mybatis-Plus工具类
 * 提供处理Mybatis-Plus查询条件的方法，以及驼峰和下划线命名转换等功能。
 */
public class MPUtil {
	public static final char UNDERLINE = '_';

	// 将JavaBean转换为Map，并将Map的键由驼峰命名转为带指定前缀的下划线命名
	public static Map allEQMapPre(Object bean, String pre) {
		Map<String, Object> map = BeanUtil.beanToMap(bean);
		return camelToUnderlineMap(map, pre);
	}

	// 将JavaBean转换为Map，并将Map的键由驼峰命名转为下划线命名
	public static Map allEQMap(Object bean) {
		Map<String, Object> map = BeanUtil.beanToMap(bean);
		return camelToUnderlineMap(map, "");
	}

	// 创建模糊查询条件，允许添加前缀
	public static Wrapper allLikePre(Wrapper wrapper, Object bean, String pre) {
		Map<String, Object> map = BeanUtil.beanToMap(bean);
		Map result = camelToUnderlineMap(map, pre);
		return genLike(wrapper, result);
	}

	// 创建模糊查询条件
	public static Wrapper allLike(Wrapper wrapper, Object bean) {
		Map result = BeanUtil.beanToMap(bean, true, true);
		return genLike(wrapper, result);
	}

	// 生成模糊查询条件
	public static Wrapper genLike(Wrapper wrapper, Map param) {
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i > 0) wrapper.and();
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String value = (String) entry.getValue();
			wrapper.like(key, value);
			i++;
		}
		return wrapper;
	}

	// 创建LIKE或EQ条件
	public static Wrapper likeOrEq(Wrapper wrapper, Object bean) {
		Map result = BeanUtil.beanToMap(bean, true, true);
		return genLikeOrEq(wrapper, result);
	}

	// 生成LIKE或EQ条件
	public static Wrapper genLikeOrEq(Wrapper wrapper, Map param) {
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i > 0) wrapper.and();
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			if (entry.getValue().toString().contains("%")) {
				wrapper.like(key, entry.getValue().toString().replace("%", ""));
			} else {
				wrapper.eq(key, entry.getValue());
			}
			i++;
		}
		return wrapper;
	}

	// 创建EQ条件
	public static Wrapper allEq(Wrapper wrapper, Object bean) {
		Map result = BeanUtil.beanToMap(bean, true, true);
		return genEq(wrapper, result);
	}

	// 生成EQ条件
	public static Wrapper genEq(Wrapper wrapper, Map param) {
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i > 0) wrapper.and();
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			wrapper.eq(key, entry.getValue());
			i++;
		}
		return wrapper;
	}

	// 创建BETWEEN条件
	public static Wrapper between(Wrapper wrapper, Map<String, Object> params) {
		for (String key : params.keySet()) {
			String columnName = key.endsWith("_start") ? key.substring(0, key.indexOf("_start")) :
					key.endsWith("_end") ? key.substring(0, key.indexOf("_end")) : null;
			if (columnName != null) {
				if (key.endsWith("_start") && StringUtils.isNotBlank(params.get(key).toString())) {
					wrapper.ge(columnName, params.get(key));
				}
				if (key.endsWith("_end") && StringUtils.isNotBlank(params.get(key).toString())) {
					wrapper.le(columnName, params.get(key));
				}
			}
		}
		return wrapper;
	}

	// 处理排序，支持多列排序
	public static Wrapper sort(Wrapper wrapper, Map<String, Object> params) {
		List<String> orderList = params.containsKey("order") ? Arrays.asList(params.get("order").toString().split(",")) : new ArrayList<String>();
		List<String> sortList = params.containsKey("sort") ? Arrays.asList(params.get("sort").toString().split(",")) : new ArrayList<String>();
		if (!orderList.isEmpty() && orderList.size() == sortList.size()) {
			for (int i = 0; i < orderList.size(); i++) {
				if ("desc".equalsIgnoreCase(orderList.get(i))) {
					wrapper.orderDesc(Arrays.asList(sortList.get(i)));
				} else {
					wrapper.orderAsc(Arrays.asList(sortList.get(i)));
				}
			}
		}
		return wrapper;
	}

	// 驼峰转下划线方法
	public static String camelToUnderline(String param) {
		if (param == null || param.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param.length());
		for (int i = 0; i < param.length(); i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE).append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 将Map的键由驼峰转为下划线命名，可指定前缀
	public static Map camelToUnderlineMap(Map param, String pre) {
		Map<String, Object> newMap = new HashMap<>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = camelToUnderline(key);
			if (!pre.isEmpty()) {
				newKey = pre.endsWith(".") ? pre + newKey : pre + "." + newKey;
			}
			newMap.put(newKey, entry.getValue());
		}
		return newMap;
	}

	public static void main(String[] ages) {
		System.out.println(camelToUnderline("ABCddfANM"));
	}
}