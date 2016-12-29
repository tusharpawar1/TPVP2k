package org.pawars.algotrading.dto;

import java.util.Date;

public class Position {
	private String Symbol = "";
	private double Price = 0;
	private int numberOfStocks = 0;
	private int PositionID = 0;
	private Date timestamp = null;
	
	public int getPositionID() {
		return PositionID;
	}
	public void setPositionID(int positionID) {
		PositionID = positionID;
	}
	
	public Position(String symbol, double Price, int number, int PositionID) {
		this.Symbol = symbol;
		this.Price = Price;
		this.PositionID = PositionID;
		this.numberOfStocks = number;
		this.setTimestamp(new Date());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + PositionID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (PositionID != other.PositionID)
			return false;
		return true;
	}
	public Position(String symbol, double Price, int number,Date timestamp, int PositionID) {
		this.Symbol = symbol;
		this.Price = Price;
		this.numberOfStocks = number;
		this.setTimestamp(timestamp);
	}

	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getNumberOfStocks() {
		return numberOfStocks;
	}
	public void setNumberOfStocks(int numberOfStocks) {
		this.numberOfStocks = numberOfStocks;
	}
	
}
