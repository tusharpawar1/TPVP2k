package org.pawars.algotrading.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pawars.algotrading.dto.Rate;

public final class Utility {
	static File file = null;
	
	public static double convertDecimal(double value){
		return Math.round(value*100)/100.0d;
	}
	public static List<Rate> getRates(String Symbol) throws Exception{
		file = new File("C:/Users/TUSHAR/workspace/AlgoTrading/data/" + Symbol + ".txt");
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
}
