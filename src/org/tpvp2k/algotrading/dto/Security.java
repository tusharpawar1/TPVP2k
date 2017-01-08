package org.tpvp2k.algotrading.dto;

import java.util.Date;
import java.util.List;

import org.tpvp2k.algotrading.connectivity.DBConnect;
import org.tpvp2k.algotrading.utilities.Utility;



public class Security {
	private String symbol = "NOT DEFINED";
	private double currPrice = 0;
	private String companyName = "";
	private double currLowestPrice= 0;
	private double currHighestPrice = 0;
	private double W52lowest = 0;
	private Date W52LowDate = null;
	private double W52highest = 0;
	private Date W52HighDate = null;
	private double W52HighLowDaysRatio = 0;
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
	private double RSI14 =0;
	private double STOCH_9_6 =0;
	private List<Rate> RateSeries = null;
	private Rate latestDailyRate = null;
	private Date lastUpdatedTimestamp = null;
	public Security(String Symbol) {
		this.symbol = Symbol;
		getRateList();
		latestDailyRate = RateSeries.get(RateSeries.size()-1);
		populateValuesFromLatestRate(latestDailyRate);
		calculate52WHighandLow();
		calculateMovingAvgs();
		calculateRSI14();
		calculateSTOCH96();
	}

	private void calculateSTOCH96() {
		double max = 0;
		double min = 999999999;
		Rate rateTmp = latestDailyRate;
		int iMasterDays = 0;
		
		double stoch = 0;
		while(iMasterDays < 6){
			int iDays = 9;

			while(iDays > 0){
				Double dblClose = rateTmp.getClose();
				System.out.println("Current Rate close = " + rateTmp.getClose());
				
				if(max < dblClose){
					max = dblClose;

				}
				if(min > dblClose){
					min = dblClose;
				}
				iDays--;
				rateTmp = rateTmp.getRefToPrevious();
			}
			rateTmp = latestDailyRate;
			for(int i = 0; i < iMasterDays; i++){
				rateTmp =  rateTmp.getRefToPrevious();
			}
			stoch =  stoch + ((rateTmp.getClose() - min)/(max -min) *100);
			rateTmp = rateTmp.getRefToPrevious();
			iMasterDays++;
		}
		STOCH_9_6 = stoch/iMasterDays;
		
		
	}

	private void calculateRSI14() {
		double avgGain = 0;
		double avgLoss = 0;
		int iDays = 14;
		Rate rateTmp = latestDailyRate;
		
		while(iDays > 0){
			
			Double dblLocal = rateTmp.getPercChange();
			
			if(dblLocal >0){
				avgGain = avgGain +dblLocal;
			}else{
				avgLoss = avgLoss +dblLocal;
			}
			
			iDays--;
			rateTmp = rateTmp.getRefToPrevious();
		}
		
		if(avgLoss == 0){
			RSI14 =100;
			return ;
			
		}
		avgGain = avgGain/14;
		avgLoss = -1*avgLoss/14;
		RSI14 = 100- (100/(1+(avgGain/avgLoss)));
		
	}

	private void calculateMovingAvgs() {
		movingAvgDay5 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,5,0)/5);
		movingAvgDay10 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,10,0)/10);
		movingAvgDay20 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,20,0)/20);
		movingAvgDay50 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,50,0)/50);
		movingAvgDay100 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,100,0)/100);
		movingAvgDay200 = Utility.convertDecimal(getLastNDaysClosingPrice(latestDailyRate,200,0)/200);
		
	}

	private double getLastNDaysClosingPrice(Rate latestRate2, int iDays, double offsetValue) {
		if(iDays<=0){
			return offsetValue;
		}
		offsetValue = getLastNDaysClosingPrice(latestRate2.getRefToPrevious(), iDays-1, (latestRate2.getClose() +offsetValue));
		return offsetValue;
	}

	private void populateValuesFromLatestRate(Rate rate) {

		this.currPrice = rate.getClose();
		this.currHighestPrice = rate.getHigh();
		this.currLowestPrice = rate.getLow();
		this.currDayOpen = rate.getOpen();
		this.support1 = rate.getSupport1();
		this.support2 = rate.getSupport2();
		this.support3 = rate.getSupport3();
		this.resistance1 = rate.getResistance1();
		this.resistance2 = rate.getResistance2();
		this.resistance3 = rate.getResistance3();
		this.lastUpdatedTimestamp = rate.getTimestamp();
	}

	private void getRateList() {
		try{
//			RateSeries = Utility.parseFileForRate(symbol);
			RateSeries = DBConnect.readRateFromDB(this.symbol);
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
		return currLowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.currLowestPrice = lowestPrice;
	}

	public double getHighestPrice() {
		return currHighestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		this.currHighestPrice = highestPrice;
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
				+ ", lowestPrice=" + currLowestPrice + ", highestPrice=" + currHighestPrice + ", W52lowest=" + W52lowest
				+ ", W52LowDate=" + W52LowDate + ", W52highest=" + W52highest + ", W52HighDate=" + W52HighDate
				+ ", W52HighLowDaysRatio=" + W52HighLowDaysRatio + ", currDayOpen=" + currDayOpen + ", prevDayClose="
				+ prevDayClose + ", resistance3=" + resistance3 + ", resistance2=" + resistance2 + ", resistance1="
				+ resistance1 + ", pivotPoint=" + pivotPoint + ", support1=" + support1 + ", support2=" + support2
				+ ", support3=" + support3 + ", movingAvgDay5=" + movingAvgDay5 + ", movingAvgDay10=" + movingAvgDay10
				+ ", movingAvgDay20=" + movingAvgDay20 + ", movingAvgDay50=" + movingAvgDay50 + ", movingAvgDay100="
				+ movingAvgDay100 + ", movingAvgDay200=" + movingAvgDay200 + "]";
	}

	public void calculate52WHighandLow(){
		int iMaxworkingDays = getRateSeries().size() <250 ? getRateSeries().size() : 250;
		
		Rate dummy = new Rate(99999999, 0, 0, 0, 0, null);
		dummy = RecursiveSearch52WHighLow(RateSeries.get(getRateSeries().size() -1), dummy, iMaxworkingDays);
		W52highest = dummy.getHigh();
		W52lowest = dummy.getLow();
		calcW52HighLowDaysRatio();
		System.out.println("52Whigh = " + W52highest + ", 52WLow = "+ W52lowest);
		
	}
	
	private Rate RecursiveSearch52WHighLow (Rate tempRate, Rate dummy, int iMaxCount){
		if(iMaxCount < 0){
			return dummy;
		}
		
		if(tempRate.getHigh() > dummy.getHigh()){
			dummy.setHigh(tempRate.getHigh());
			this.W52HighDate = tempRate.getTimestamp();
		}
		if(tempRate.getLow() < dummy.getLow() && tempRate.getLow() > 0 ){
			dummy.setLow(tempRate.getLow());
			this.W52LowDate = tempRate.getTimestamp();
		}
		dummy = RecursiveSearch52WHighLow(tempRate.getRefToPrevious(), dummy, iMaxCount-1);
		
		return dummy;
	}
	
	private void calcW52HighLowDaysRatio(){
		
		int HighdaysDiff = calcDaysDiff(W52HighDate);
		int LowdaysDiff = calcDaysDiff(W52LowDate);
		
		W52HighLowDaysRatio = Utility.convertDecimal(Math.log10((HighdaysDiff*100)/(LowdaysDiff <= 0?1: LowdaysDiff)));
		
	}
	private int calcDaysDiff(Date startDate){
		long startTime = startDate.getTime();
		long endTime = (new Date()).getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
	
		return (int) diffDays;
	}

	public double getW52HighLowDaysRatio() {
		return W52HighLowDaysRatio;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public double getRSI14() {
		return Math.round(RSI14);
	}

	public double getSTOCH_9_6() {
		return Math.round(STOCH_9_6);
	}
}
