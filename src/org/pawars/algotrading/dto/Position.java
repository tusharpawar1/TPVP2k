package org.pawars.algotrading.dto;

import java.util.Date;

public class Position {
	String Symbol = "";
	double Price = 0;
	int numberOfStocks = 0;
	Date DateTimeOfPurchase = new Date();
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
	public int getNumberOfStocks() {
		return numberOfStocks;
	}
	public void setNumberOfStocks(int numberOfStocks) {
		this.numberOfStocks = numberOfStocks;
	}
	public Date getDateTimeOfPurchase() {
		return DateTimeOfPurchase;
	}
	public void setDateTimeOfPurchase(Date dateTimeOfPurchase) {
		DateTimeOfPurchase = dateTimeOfPurchase;
	}
	
}
