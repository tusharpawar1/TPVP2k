package org.pawars.algotrading.connectivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pawars.algotrading.dto.Rate;

public class DBConnect {
	private static Connection Conn = null;
	private static Statement stmt = null;
	private static String insQuery_1 = "insert into at_historical_daily(pk_id,symbol,low,high,close,open,volume,timestamp,change) values "
			+ "(?,	?,	?,	?,	?,	?,	?,	?,	?)";
	private static String selQuery_2 = "select pk_id,symbol,low,high,close,open,volume,timestamp,change from at_historical_daily d order by timestamp ;";

	private String Create_SQL_0 = "select * from at_historical_daily where 1=2;";
	private String Create_SQL_1 = "create table at_historical_daily("
			+ "pk_id INTEGER PRIMARY KEY , "
			+ "symbol varchar(10) not null, "
			+ "low double not null,"
			+ "high double not null,"
			+ "close double not null,"
			+ "open double not null,"
			+ "volume int null,"
			+ "timestamp datetime null,"
			+ "change double null);";
	
	public DBConnect() {
		DBinit();
		
	}
	public void OneTimeSetup() {
		try{ 
			stmt.executeUpdate(Create_SQL_0);		}catch(SQLException ex){ System.out.println(ex);		try{ 
			stmt.executeUpdate(Create_SQL_1);		}catch(SQLException ex1){ 
				System.out.println(ex);}
		}
	
	}
	private void DBinit(){
		try {
			Class.forName("org.sqlite.JDBC");
			Conn = DriverManager.getConnection("jdbc:sqlite:algoTrading.db");
			Conn.setAutoCommit(true);
			stmt = Conn.createStatement();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void insertRow(String SQLrow) throws SQLException{
		stmt.executeUpdate(SQLrow);
	}
	public List<Rate> getRatesFromDB(String symbol){
		
		return null;
	}
	public void fullSecuritiesDataLoadToDB(String[] Symbol) throws Exception{
		PreparedStatement stmt = Conn.prepareStatement(insQuery_1);
		int iBatchSize = 500;
		File file = null;
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
	public Connection getConn() {
		return Conn;
	}
	public Statement getStmt() {
		return stmt;
	}

	public static List<Rate> readRateFromDB(String Symbol) throws Exception {
		List<Rate> Series = new ArrayList<Rate>();
		Rate previousRate = new Rate();
		ResultSet rs = stmt.executeQuery(selQuery_2);
		while (rs.next()) {
			
			Date dt = rs.getDate("timestamp");
			double close = rs.getDouble("close");
			double open  = rs.getDouble("open");
			double high  = rs.getDouble("high");
			double low   = rs.getDouble("low");
			int volume 	 = rs.getInt("volume");
			double change = rs.getDouble("change");
			int pkID 	  = rs.getInt("pk_id");
			Rate rate = new Rate(low, high, close, open, volume, dt, change, pkID, previousRate);
			previousRate = rate;
			Series.add(rate);
		}
		previousRate.calculate();
		
		return Series;
	}

}
