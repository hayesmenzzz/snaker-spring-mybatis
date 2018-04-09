package com.simple.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simple.spring.beans.Moveable;

public class BeanFactoryTest {

	public static void main(String[] args) {
		// Spring利用本身读取XML配置文件，并且实现了BeanFactory接口。我们只需要在客户端传入配置文件名，传入配置文件的ID，就能根据Id找到相应的class产生的对象，并将对象作为一个bean反馈回来。 
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Object factoryBean = factory.getBean("car");
		Moveable m = (Moveable) factoryBean;
		m.run();
	}
}
