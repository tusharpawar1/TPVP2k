package org.pawars.algotrading;

import java.util.ArrayList;
import java.util.List;

import org.pawars.algotrading.connectivity.MasterConnect;
import org.pawars.algotrading.dto.Security;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static String[] Securities = {"SBIN","HDFC","HDBK"};
	
	public static void main(String[] args)  throws Exception {
		
//		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");
//		MasterConnect mConn = (MasterConnect)factory.getBean("masterConn");
//		factory.close();
		List<Security> lstSecurities = new ArrayList<Security>();
		for(String strSec : Securities){
			lstSecurities.add(new Security(strSec));
		}
		for(Security tmp : lstSecurities){
			System.out.println(tmp.getSymbol());
			System.out.println(tmp.getW52highest());
			System.out.println(tmp.getW52lowest());
			System.out.println(tmp.getW52HighLowDaysRatio());
			System.out.println("---------------------------------");
		}
		
		System.out.println("I am here");
				
	
	}

}
