package org.pawars.algotrading.dto;

import java.util.Date;

public class Series {

	private double low = 0;
	private double high = 0;
	private double close = 0;
	private double open = 0;
	private int volume = 0;
	private double pivot = 0;
	private double resistance3 = 0;
	private double resistance2 = 0;
	private double resistance1 = 0;
	private double pivotPoint = 0;
	private double support1 = 0;
	private double support2 = 0;
	private double support3 = 0;
	
	private Date timestamp = null;

	public Series(double low, double high, double close, double open, int volume) {
		this.low = low;
		this.high = high;
		this.close = close;
		this.open = open;
		this.volume = volume;
		calculate();
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

	private void calculate() {
		pivot = (high + low + close) / 3;
		R1
	}
}
