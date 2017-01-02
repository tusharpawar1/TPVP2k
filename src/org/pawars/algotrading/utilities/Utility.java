package org.pawars.algotrading.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pawars.algotrading.connectivity.DBConnect;
import org.pawars.algotrading.dto.Rate;

public final class Utility {
	static File file = null;
	private static DBConnect dbConn = null;
	private static String insQuery_1 = "insert into at_historical_daily(pk_id,symbol,low,high,close,open,volume,timestamp,change) values "
			+ "(?,	?,	?,	?,	?,	?,	?,	?,	?)";
	public static double convertDecimal(double value){
		return Math.round(value*100)/100.0d;
	}
	public static List<Rate> getRates(String Symbol) throws Exception{
		file = new File("data/" + Symbol + ".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		DateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		List<Rate> Series = new ArrayList<Rate>();
		Rate previousRate= new Rate();
		while(br.ready()){
			String line = br.readLine();
			if(line == null || line.equals("")){
				continue;
			}

			String[] str = line.split("\\|");
			Date dt = sdf.parse(str[0]);
			double close = Double.parseDouble(str[1]);
			double open = Double.parseDouble(str[2]);
			double high = Double.parseDouble(str[3]);
			double low = Double.parseDouble(str[4]);
			int volume = 0 ;//Integer.parseInt(str[5]);
			if(str[5].endsWith("M")){
				volume = (int)(Double.parseDouble(str[5].substring(0, str[5].length()-1))*1000000);
			}
			else if (str[5].endsWith("K")){
				volume = (int)(Double.parseDouble(str[5].substring(0, str[5].length()-1))*1000);
			}
			double change = 0;
			if(str[6].endsWith("%")){
				change = Double.parseDouble(str[6].substring(0, str[6].length()-1));
			}

			Rate rate = new Rate(low, high, close, open, volume, dt, change, previousRate);
			previousRate= rate;


			Series.add(rate);
		}
		previousRate.calculate();
		br.close();
		return Series;
	}
	public static void fullSecuritiesDataLoadToDB(String[] Symbol) throws Exception{
		PreparedStatement stmt = dbConn.getConn().prepareStatement(insQuery_1);
		int iBatchSize = 500;
		for(String forLoopSymbol : Symbol){
			file = new File("data/" + forLoopSymbol + ".txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			DateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			try{
				int iCounter = 0;
				while(br.ready()){

					String line = br.readLine();
					if(line == null || line.equals("")){
						continue;
					}

					String[] str = line.split("\\|");
					Date dt = sdf.parse(str[0]);
					double close = Double.parseDouble(str[1]);
					double open = Double.parseDouble(str[2]);
					double high = Double.parseDouble(str[3]);
					double low = Double.parseDouble(str[4]);
					int volume = 0 ;//Integer.parseInt(str[5]);
					if(str[5].endsWith("M")){
						volume = (int)(Double.parseDouble(str[5].substring(0, str[5].length()-1))*1000000);
					}
					else if (str[5].endsWith("K")){
						volume = (int)(Double.parseDouble(str[5].substring(0, str[5].length()-1))*1000);
					}
					double change = 0;
					if(str[6].endsWith("%")){
						change = Double.parseDouble(str[6].substring(0, str[6].length()-1));
					}

					//					stmt.setInt		(1, iCounter); 
					stmt.setString	(2, Symbol[0]);
					stmt.setDouble	(3, low);
					stmt.setDouble	(4, high);
					stmt.setDouble	(5, close);
					stmt.setDouble	(6, open);
					stmt.setInt		(7, volume);
					stmt.setDate	(8, new java.sql.Date(dt.getTime()));
					stmt.setDouble	(9, change);
					stmt.addBatch();
					iCounter++;
					if(iCounter >= iBatchSize){
						stmt.executeBatch();
					}


				}
				stmt.executeBatch();
			}catch(Exception ex){
				System.out.println(ex);
			}
			br.close();
		}
	}
	public static void setDbConn(DBConnect dbConn) {
		Utility.dbConn = dbConn;
	}
}
