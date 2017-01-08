package org.tpvp2k.algotrading;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tpvp2k.algotrading.connectivity.DBConnect;
import org.tpvp2k.algotrading.constants.Constant;

public class Dataloader {

	public static void main(String[] args) throws Exception{
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");

		DBConnect dbConn = (DBConnect)factory.getBean("dbConn");
		dbConn.OneTimeSetup();
		dbConn.fullSecuritiesDataLoadToDB(Constant.Securities);
		factory.close();
		
	}
	

}
