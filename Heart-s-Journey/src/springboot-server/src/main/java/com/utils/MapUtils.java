package com.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 * 继承自HashMap，提供了更便捷的方法来操作map对象，同时包含将对象转换为map和将map转换为对象的工具方法。
 */
public class MapUtils extends HashMap<String, Object> {

    /**
     * 重写put方法，使其返回当前对象，便于链式调用。
     *
     * @param key   键
     * @param value 值
     * @return 返回当前MapUtils实例，支持链式操作。
     */
    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value); // 调用父类HashMap的put方法
        return this; // 返回当前对象，支持链式操作
    }

    /**
     * 将任意对象的字段映射到一个Map对象。
     *
     * @param obj 要转换的对象
     * @return 包含对象字段名及其值的Map
     * @throws IllegalAccessException 如果字段不可访问
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass(); // 获取对象的Class实例
        for (Field field : clazz.getDeclaredFields()) { // 遍历所有字段
            field.setAccessible(true); // 设置字段为可访问
            map.put(field.getName(), field.get(obj)); // 将字段名和字段值放入Map
        }
        return map;
    }

    /**
     * 将一个Map对象转换为指定类型的对象。
     *
     * @param map   包含对象数据的Map
     * @param clazz 目标对象的类类型
     * @return 转换后的对象实例
     * @throws IllegalAccessException    如果实例化过程中字段不可访问
     * @throws InstantiationException    如果实例化对象失败
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance(); // 创建类的实例
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = null;
            try {
                field = clazz.getDeclaredField(entry.getKey()); // 尝试获取字段
                field.setAccessible(true); // 设置字段为可访问
                field.set(obj, entry.getValue()); // 将map的值设置到对象的字段
            } catch (NoSuchFieldException ignored) { // 忽略找不到字段的异常
            }
        }
        return obj;
    }
}