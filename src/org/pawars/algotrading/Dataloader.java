package org.pawars.algotrading;

import org.pawars.algotrading.connectivity.DBConnect;
import org.pawars.algotrading.constants.Constant;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Dataloader {

	public static void main(String[] args) throws Exception{
		
		AbstractApplicationContext factory = new ClassPathXmlApplicationContext("Spring-Beans.xml");

		DBConnect dbConn = (DBConnect)factory.getBean("dbConn");
		dbConn.OneTimeSetup();
		dbConn.fullSecuritiesDataLoadToDB(Constant.Securities);
		factory.close();
		
	}
	

}
