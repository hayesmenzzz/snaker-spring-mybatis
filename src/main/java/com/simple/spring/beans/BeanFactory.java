package com.simple.spring.beans;

/**
 * Spring模拟
 * 定义BeanFactory接口和getBean方法
 * @author Administrator
 *
 */
public interface BeanFactory {
	Object getBean(String id);
}
