package com.simple.spring;


import com.simple.spring.beans.BeanFactory;
import com.simple.spring.beans.ClassPathXmlApplicationContext;
import com.simple.spring.beans.Moveable;

public class SpringBeanFactoryTest {

	public static void main(String[] args) {
		try {
			BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			Object bean = beanFactory.getBean("car");
			Moveable m = (Moveable) bean;
			m.run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
