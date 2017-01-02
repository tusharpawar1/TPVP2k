package org.pawars.algotrading;

import static org.pawars.algotrading.constants.Constant.*;

import java.util.ArrayList;
import java.util.List;


import org.pawars.algotrading.connectivity.DBConnect;
import org.pawars.algotrading.connectivity.MasterConnect;
import org.pawars.algotrading.dto.Rate;
import org.pawars.algotrading.dto.Security;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	
	public static void main(String[] args)  throws Exception {
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");
//		MasterConnect mConn = (MasterConnect)factory.getBean("masterConn");
		DBConnect dbConn = (DBConnect)factory.getBean("dbConn");
		 
		List<Security> lstSecurities = new ArrayList<Security>();
		for(String strSec : Securities){
			lstSecurities.add(new Security(strSec));
		}
		for(Security tmp : lstSecurities){
			System.out.println(tmp.getSymbol());
			System.out.println(tmp.getW52highest());
			System.out.println(tmp.getW52lowest());
			
//			insertIntoDB(tmp.getRateSeries());
			
			System.out.println(tmp.getW52HighLowDaysRatio());
			System.out.println("---------------------------------");
		}
		
		System.out.println("I am here");
				
		factory.close();	
	}

}
