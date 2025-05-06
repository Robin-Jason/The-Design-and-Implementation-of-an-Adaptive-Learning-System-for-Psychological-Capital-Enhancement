package com.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 * 实现了ApplicationContextAware接口，允许访问Spring容器中的Beans。
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	// 静态变量存储应用上下文
	public static ApplicationContext applicationContext;

	/**
	 * 通过实现ApplicationContextAware接口的setApplicationContext方法，自动注入ApplicationContext。
	 *
	 * @param applicationContext Spring应用上下文
	 * @throws BeansException 如果上下文注入失败
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;  // 将传入的ApplicationContext存储到静态变量中
	}

	/**
	 * 通过名称获取Bean。
	 *
	 * @param name Bean的名称
	 * @return 返回Bean的实例
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 通过名称和类类型获取Bean。
	 *
	 * @param name Bean的名称
	 * @param requiredType Bean的类类型
	 * @return 返回指定类型的Bean实例
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 检查是否存在给定名称的Bean。
	 *
	 * @param name Bean的名称
	 * @return 如果存在该名称的Bean返回true，否则返回false
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 检查给定名称的Bean是否为单例。
	 *
	 * @param name Bean的名称
	 * @return 如果该Bean为单例返回true，否则返回false
	 */
	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	/**
	 * 获取给定Bean名称的类型。
	 *
	 * @param name Bean的名称
	 * @return 返回Bean的类型
	 */
	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
}