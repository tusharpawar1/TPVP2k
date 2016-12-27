package org.pawars.algotrading.dto;

import java.util.Date;

public class Rate {

	private double low = 0;
	private double high = 0;
	private double close = 0;
	private double open = 0;
	private int volume = 0;
	private double pivot = 0;
	private double resistance3 = 0;
	private double resistance2 = 0;
	private double resistance1 = 0;
	private double support1 = 0;
	private double support2 = 0;
	private double support3 = 0;
	private double previousDayClose = 0;
	private Date timestamp = null;
	private Rate refToPrevious = null;

	public Rate() {
	
	}
	public Rate(double low, double high, double close, double open, int volume, Date timestamp) {
		this.low = low;
		this.high = high;
		this.close = close;
		this.open = open;
		this.volume = volume;
		this.timestamp = timestamp;
		
	}
	public Rate(double low, double high, double close, double open, int volume, Date timestamp, Rate previousRate) {
		this.low = low;
		this.high = high;
		this.close = close;
		this.open = open;
		this.volume = volume;
		this.timestamp = timestamp;
		this.previousDayClose = previousRate.getClose();
		this.refToPrevious = previousRate;
		
	}
	
	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public void calculate() {
		pivot = (high + low + previousDayClose) / 3;
		pivot = Math.round(pivot*100)/100.0d;
		
		resistance1 = (2 * pivot) - low;
		resistance1 = Math.round(resistance1*100)/100.0d;
		
		support1 = (2 * pivot) - high;
		support1 = Math.round(support1 *100)/100.0d;
		
		resistance2 = pivot + (high - low);
		resistance2 = Math.round(resistance2 *100)/100.0d;
		
		support2 = pivot - (high - low);
		support2 = Math.round(support2 *100)/100.0d;
		
		resistance3 = high + 2 * (pivot - low);
		resistance3 = Math.round(resistance3 *100)/100.0d;
		
		support3 = low - 2 * (high - pivot);
		support3 = Math.round(support3 *100)/100.0d;
		
		
	}

	public double getPivot() {
		return pivot;
	}

	public double getResistance3() {
		return resistance3;
	}

	public double getResistance2() {
		return resistance2;
	}

	public double getResistance1() {
		return resistance1;
	}

	public double getSupport1() {
		return support1;
	}

	public double getSupport2() {
		return support2;
	}

	public double getSupport3() {
		return support3;
	}

	public double getPreviousDayClose() {
		return previousDayClose;
	}

	public Rate getRefToPrevious() {
		return refToPrevious;
	}
	@Override
	public String toString() {
		return "Rate [low=" + low + ", high=" + high + ", close=" + close + ", open=" + open + ", volume=" + volume
				+ ", pivot=" + pivot + ", resistance3=" + resistance3 + ", resistance2=" + resistance2
				+ ", resistance1=" + resistance1 + ", support1=" + support1 + ", support2=" + support2 + ", support3="
				+ support3 + ", previousDayClose=" + previousDayClose + ", timestamp=" + timestamp + "]";
	}
}
