package org.pawars.algotrading.dto;

import java.util.Date;

import org.pawars.algotrading.utilities.Utility;

public class Rate {
	private int pkID = 0;
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
	private double percChange = 0;
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
	public Rate(double low, double high, double close, double open, int volume, Date timestamp, double percChange, Rate previousRate) {
		this.low = low;
		this.high = high;
		this.close = close;
		this.open = open;
		this.volume = volume;
		this.timestamp = timestamp;
		this.previousDayClose = previousRate.getClose();
		this.refToPrevious = previousRate;
		this.percChange = percChange;
	}
	public Rate(double low, double high, double close, double open, int volume, Date timestamp, double percChange, int PKID, Rate previousRate) {
		this.low = low;
		this.high = high;
		this.close = close;
		this.open = open;
		this.volume = volume;
		this.timestamp = timestamp;
		this.previousDayClose = previousRate.getClose();
		this.refToPrevious = previousRate;
		this.percChange = percChange;
		this.pkID = PKID;
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
		return new Date(timestamp.getTime());
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
		pivot = Utility.convertDecimal(pivot);
		
		resistance1 = (2 * pivot) - low;
		resistance1 = Utility.convertDecimal(resistance1);
		
		support1 = (2 * pivot) - high;
		support1 = Utility.convertDecimal(support1 );
		
		resistance2 = pivot + (high - low);
		resistance2 = Utility.convertDecimal(resistance2 );
		
		support2 = pivot - (high - low);
		support2 = Utility.convertDecimal(support2 );
		
		resistance3 = high + 2 * (pivot - low);
		resistance3 = Utility.convertDecimal(resistance3 );
		
		support3 = low - 2 * (high - pivot);
		support3 = Utility.convertDecimal(support3 );
		
		
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
				+ support3 + ", previousDayClose=" + previousDayClose + ", percChange=" + percChange + ", timestamp="
				+ timestamp + "]";
	}
}
