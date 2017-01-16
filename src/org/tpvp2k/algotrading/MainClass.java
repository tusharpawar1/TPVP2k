package org.tpvp2k.algotrading;

import static org.tpvp2k.algotrading.constants.Constant.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tpvp2k.algotrading.connectivity.DBConnect;
import org.tpvp2k.algotrading.connectivity.MasterConnect;
import org.tpvp2k.algotrading.dto.Rate;
import org.tpvp2k.algotrading.dto.Security;
import org.tpvp2k.algotrading.strategies.IntradayStrategy;
import org.tpvp2k.algotrading.strategies.Strategy;

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
			System.out.println("RSI14 = " + tmp.getRSI14());
			System.out.println("STOCH_96 = " + tmp.getSTOCH_9_6());

			System.out.println(tmp.getW52HighLowDaysRatio());
			System.out.println("---------------------------------");
			Strategy intraStr = new IntradayStrategy(tmp);
			intraStr.backTestStrategy();
		}
		
		System.out.println("I am here");
				
		factory.close();	
	}

}
