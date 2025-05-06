package com.utils;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 脱敏工具类
 * 用于处理敏感数据的脱敏操作
 */
public class DeSensUtil {

    /**
     * 对PageUtils对象中的列表数据进行脱敏处理
     *
     * @param page 包含列表数据的分页工具类
     * @param deSens 包含字段名和脱敏规则的映射
     */
    public static void desensitize(PageUtils page, Map<String, String> deSens) {
        // 检查传入的page对象是否为空，且其列表属性是否包含元素
        if (null != page && !CollectionUtils.isEmpty(page.getList())) {
            desensitize(page.getList(), deSens);  // 对列表进行脱敏处理
        }
    }

    /**
     * 对对象列表进行脱敏处理
     *
     * @param objects 待脱敏的对象列表
     * @param deSens 包含字段名和脱敏规则的映射
     */
    public static void desensitize(List<?> objects, Map<String, String> deSens) {
        for (Object obj : objects) {
            if (obj != null) {
                desensitize(obj, deSens);  // 对单个对象进行脱敏处理
            }
        }
    }

    /**
     * 对单个对象进行脱敏处理
     *
     * @param obj 待脱敏的对象
     * @param deSens 包含字段名和脱敏规则的映射
     */
    public static void desensitize(Object obj, Map<String, String> deSens) {
        Class<?> clazz = obj.getClass();  // 获取对象的类类型
        while (clazz != null) {  // 遍历所有父类字段
            Field[] fields = clazz.getDeclaredFields();  // 获取当前类的所有字段
            for (Field field : fields) {
                desensitizeField(obj, field, deSens);  // 对每个字段进行脱敏处理
            }
            clazz = clazz.getSuperclass();  // 获取父类类型，继续循环
        }
    }

    /**
     * 对单个字段进行脱敏处理
     *
     * @param obj 包含待脱敏字段的对象
     * @param field 待脱敏的字段
     * @param deSens 包含字段名和脱敏规则的映射
     */
    private static void desensitizeField(Object obj, Field field, Map<String, String> deSens) {
        field.setAccessible(true);  // 确保私有字段也可访问
        try {
            String fieldName = field.getName();  // 获取字段名称
            String rule = deSens.get(fieldName);  // 获取脱敏规则
            if (rule != null) {
                Object value = field.get(obj);  // 获取字段的值
                if (value instanceof String) {
                    String stringValue = (String) value;
                    String desensitizedValue = applyDesensitizationRule(stringValue, rule);  // 应用脱敏规则
                    field.set(obj, desensitizedValue);  // 设置脱敏后的值
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();  // 处理反射访问异常
        }
    }

    /**
     * 根据脱敏类型应用具体的脱敏规则
     *
     * @param content 待脱敏的内容
     * @param type 脱敏规则类型
     * @return 脱敏后的内容
     */
    private static String applyDesensitizationRule(String content, String type) {
        switch (type) {
            case "名": //保留姓名的第一个字，其余部分用*
                return content != null && content.length() > 1 ?
                        content.charAt(0) + repeatChar('*', content.length() - 1) : content;
            case "手": //将手机号中间四位数字替换为*
                return content != null && content.length() == 11 ?
                        content.substring(0, 3) + "****" + content.substring(7) : content;
            case "身": //将身份证号码的第7位到第16位用*
                return content != null && content.length() >= 18 ?
                        content.substring(0, 6) + repeatChar('*', 10) + content.substring(16) : content;
            case "卡": //银行卡号保留前6位和后4位，其他部分用*
                return content != null && content.length() >= 10 ?
                        content.substring(0, 6) + repeatChar('*', content.length() - 10) + content.substring(content.length() - 4) : content;
            case "邮": //邮箱保留首字符及@符号后的域名部分，其他部分用*
                if (content != null && content.contains("@")) {
                    int atIndex = content.indexOf('@');
                    return content.charAt(0) + repeatChar('*', atIndex - 1) + content.substring(atIndex);
                }
                return content;
            default:
                return content;  // 未定义的规则直接返回原内容
        }
    }

    /**
     * 生成重复的字符
     *
     * @param ch 要重复的字符
     * @param count 重复的次数
     * @return 生成的字符串
     */
    private static String repeatChar(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(ch);  // 追加指定的字符
        }
        return sb.toString();
    }
}