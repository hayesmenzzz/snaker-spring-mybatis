package com.simple.spring.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


public class ClassPathXmlApplicationContext implements BeanFactory{

	//把所有配置文件里的东西读出来保存在map里面 
	private Map<String,Object> container = new HashMap<String,Object>();
	
	//定义构造函数
	public ClassPathXmlApplicationContext(String fileName) throws Exception {
		//引入读取xml的jdom
		SAXBuilder sb = new SAXBuilder();
		//拿到配置文件（this.getClass().getClassLoader().getResourceAsStream(fileName) path 以“/”开头 从classpath中寻找；不以“/”开头则从此类所在包下获取资源）
		Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream(fileName));
		Element rootElement = doc.getRootElement();
		List<?> selectNodes = XPath.selectNodes(rootElement, "/beans/bean");
		
		for(int i=0;i< selectNodes.size();i++){
			Element bean = (Element) selectNodes.get(i);
			String id = bean.getAttributeValue("id");
			String clazz = bean.getAttributeValue("class");
			Object newInstance = Class.forName(clazz).newInstance();
			container.put(id, newInstance);//存放对象
			//相关过程输出
			System.out.println("[id="+id);
			System.out.println("通过id拿到具体的class信息："+clazz+"]");
		}
	}
	
	//调用getBean将拿到的信息返给客户
	@Override
	public Object getBean(String id) {
		return container.get(id);
	}

}
