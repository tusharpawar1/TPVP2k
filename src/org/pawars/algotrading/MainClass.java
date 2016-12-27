package org.pawars.algotrading;

import org.pawars.algotrading.connectivity.MasterConnect;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args)  throws Exception {
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");
		MasterConnect mConn = (MasterConnect)factory.getBean("masterConn");
		
		factory.close();
	}

}
