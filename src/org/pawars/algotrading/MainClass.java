package org.pawars.algotrading;

import org.pawars.algotrading.connectivity.MasterConnect;
import org.pawars.algotrading.dto.Security;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args)  throws Exception {
		
//		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");
//		MasterConnect mConn = (MasterConnect)factory.getBean("masterConn");
//		factory.close();
		Security sec = new Security("SBIN");
		int iSize = sec.getRateSeries().size();
		sec.calculate52WHighandLow();
		System.out.println(sec.getRateSeries().get(iSize-1));
				
	
	}

}
