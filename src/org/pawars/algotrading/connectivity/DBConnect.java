package org.pawars.algotrading.connectivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	private Connection Conn = null;
	private Statement stmt = null;
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
	private String Create_SQL_2 = "drop table at_historical_monthly;";
	

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
	public Connection getConn() {
		return Conn;
	}
	public Statement getStmt() {
		return stmt;
	}


}
