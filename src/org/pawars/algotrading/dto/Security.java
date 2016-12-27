package org.pawars.algotrading.dto;

import java.util.List;

import org.pawars.algotrading.utilities.Utility;



public class Security {
	private String symbol = "NOT DEFINED";
	private double currPrice = 0;
	private String companyName = "";
	private double lowestPrice = 0;
	private double highestPrice = 0;
	private double W52lowest = 0;
	private double W52highest = 0;
	private double currDayOpen = 0;
	private double prevDayClose = 0;
	private double resistance3 = 0;
	private double resistance2 = 0;
	private double resistance1 = 0;
	private double pivotPoint = 0;
	private double support1 = 0;
	private double support2 = 0;
	private double support3 = 0;
	private double movingAvgDay5 = 0;
	private double movingAvgDay10 = 0;
	private double movingAvgDay20 = 0;
	private double movingAvgDay50 = 0;
	private double movingAvgDay100 = 0;
	private double movingAvgDay200 = 0;
	private List<Rate> RateSeries = null;

	public Security(String Symbol) {
		this.symbol = Symbol;
		getRateList();
	}

	private void getRateList() {
		try{
			RateSeries = Utility.getRates(symbol);
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public double getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}

	public double getW52lowest() {
		return W52lowest;
	}

	public void setW52lowest(double w52lowest) {
		W52lowest = w52lowest;
	}

	public double getW52highest() {
		return W52highest;
	}

	public void setW52highest(double w52highest) {
		W52highest = w52highest;
	}

	public double getCurrDayOpen() {
		return currDayOpen;
	}

	public void setCurrDayOpen(double currDayOpen) {
		this.currDayOpen = currDayOpen;
	}

	public double getPrevDayClose() {
		return prevDayClose;
	}

	public void setPrevDayClose(double prevDayClose) {
		this.prevDayClose = prevDayClose;
	}

	public double getResistance3() {
		return resistance3;
	}

	public void setResistance3(double resistance3) {
		this.resistance3 = resistance3;
	}

	public double getResistance2() {
		return resistance2;
	}

	public void setResistance2(double resistance2) {
		this.resistance2 = resistance2;
	}

	public double getResistance1() {
		return resistance1;
	}

	public void setResistance1(double resistance1) {
		this.resistance1 = resistance1;
	}

	public double getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(double pivotPoint) {
		this.pivotPoint = pivotPoint;
	}

	public double getSupport1() {
		return support1;
	}

	public void setSupport1(double support1) {
		this.support1 = support1;
	}

	public double getSupport2() {
		return support2;
	}

	public void setSupport2(double support2) {
		this.support2 = support2;
	}

	public double getSupport3() {
		return support3;
	}

	public void setSupport3(double support3) {
		this.support3 = support3;
	}

	public double getMovingAvgDay5() {
		return movingAvgDay5;
	}

	public void setMovingAvgDay5(double movingAvgDay5) {
		this.movingAvgDay5 = movingAvgDay5;
	}

	public double getMovingAvgDay10() {
		return movingAvgDay10;
	}

	public void setMovingAvgDay10(double movingAvgDay10) {
		this.movingAvgDay10 = movingAvgDay10;
	}

	public double getMovingAvgDay20() {
		return movingAvgDay20;
	}

	public void setMovingAvgDay20(double movingAvgDay20) {
		this.movingAvgDay20 = movingAvgDay20;
	}

	public double getMovingAvgDay50() {
		return movingAvgDay50;
	}

	public void setMovingAvgDay50(double movingAvgDay50) {
		this.movingAvgDay50 = movingAvgDay50;
	}

	public double getMovingAvgDay100() {
		return movingAvgDay100;
	}

	public void setMovingAvgDay100(double movingAvgDay100) {
		this.movingAvgDay100 = movingAvgDay100;
	}

	public double getMovingAvgDay200() {
		return movingAvgDay200;
	}

	public void setMovingAvgDay200(double movingAvgDay200) {
		this.movingAvgDay200 = movingAvgDay200;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Security))
			return false;
		Security other = (Security) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	public List<Rate> getRateSeries() {
		return RateSeries;
	}

	@Override
	public String toString() {
		return "Security [symbol=" + symbol + ", currPrice=" + currPrice + ", companyName=" + companyName
				+ ", lowestPrice=" + lowestPrice + ", highestPrice=" + highestPrice + ", W52lowest=" + W52lowest
				+ ", W52highest=" + W52highest + ", currDayOpen=" + currDayOpen + ", prevDayClose=" + prevDayClose
				+ ", resistance3=" + resistance3 + ", resistance2=" + resistance2 + ", resistance1=" + resistance1
				+ ", pivotPoint=" + pivotPoint + ", support1=" + support1 + ", support2=" + support2 + ", support3="
				+ support3 + ", movingAvgDay5=" + movingAvgDay5 + ", movingAvgDay10=" + movingAvgDay10
				+ ", movingAvgDay20=" + movingAvgDay20 + ", movingAvgDay50=" + movingAvgDay50 + ", movingAvgDay100="
				+ movingAvgDay100 + ", movingAvgDay200=" + movingAvgDay200 + ", RateSeries=" + RateSeries + "]";
	}

	public void calculate52WHighandLow(){
		int iMaxworkingDays = getRateSeries().size() <250 ? getRateSeries().size() : 250;
		double TempHigh = 0;
		double TempLow = 9999999;
		for (Rate r: RateSeries){
			if(r.getHigh() > TempHigh){
				TempHigh=r.getHigh();
			}
			if(r.getLow() < TempLow){
				TempLow=r.getLow();
			}
		}
		W52highest = TempHigh;
		W52lowest = TempLow;
		
		System.out.println("52Whigh = " + W52highest + ", 52WLow = "+ W52lowest);
		
	}

}
