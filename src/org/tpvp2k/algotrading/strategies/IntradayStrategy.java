package org.tpvp2k.algotrading.strategies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;

import org.tpvp2k.algotrading.dto.Rate;
import org.tpvp2k.algotrading.dto.Security;

public class IntradayStrategy implements Strategy {
	private Security Sec = null;

	public IntradayStrategy(Security securityToBeProcessed) {
		Sec = securityToBeProcessed;
		// backTestStrategy();
	}

	public void backTestStrategy() throws Exception {
		File file = new File("C:/Users/TUSHAR/workspace/AlgoTrading/output/output." + Sec.getSymbol() + ".txt");
		FileWriter br = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(br);
		LinkedList<Rate> tempRateList = (LinkedList<Rate>) Sec.getRateSeries();
		Iterator<Rate> itr = tempRateList.iterator();
		int iCnt = 0;
		double myboughtPrice = 0;
		double myTotalProfit = 0;
		boolean blnBuyOrHold = false;
		int iNoOfdayHold = 0;
		while (itr.hasNext()) {
			iNoOfdayHold++;
			Rate tmpRate = itr.next();

			if (iCnt < 1 || !blnBuyOrHold) {
				myboughtPrice = tmpRate.getBreakEvenIntradayPrice();
				blnBuyOrHold = true;
				iCnt++;
				bw.write("BOUGHT" + "," + myboughtPrice + "\n");

				continue;
			}
			if (blnBuyOrHold) {
				if (iNoOfdayHold>3) {
//					System.out.println("Loss");
					blnBuyOrHold = false;
//					myTotalProfit = tmpRate.getOpen() -  myboughtPrice ;
					
					bw.write("Loss" + "," + myboughtPrice + "," + tmpRate.getOpen() + ","+iNoOfdayHold+ "\n");
					iNoOfdayHold = 0;

				}  else if (iNoOfdayHold == 3 && (tmpRate.getOpen() < myboughtPrice * 0.98)) {
					System.out.println("Loss");
					blnBuyOrHold = false;
//					myTotalProfit = myTotalProfit -  myboughtPrice * 0.03;
					bw.write("Loss" + "," + myboughtPrice + "," + myboughtPrice * 0.97 + ","+iNoOfdayHold+ "\n");
					iNoOfdayHold = 0;

				} else if ( (tmpRate.getHigh() > myboughtPrice * 1.004)) {
					System.out.println("Profit");
					
//					myTotalProfit = myTotalProfit + (myboughtPrice * 1.004);
					blnBuyOrHold = false;
					bw.write("Profit" + "," + myboughtPrice + "," + myboughtPrice * 1.004 + ","+iNoOfdayHold+ "\n");
					iNoOfdayHold = 0;

				} 
				else {
					System.out.println("Hold");
					bw.write("Hold" + "," + "0" + ",,"+iNoOfdayHold+"\n");
					
				}
			}

		}
		bw.flush();
		bw.close();
	}
}
