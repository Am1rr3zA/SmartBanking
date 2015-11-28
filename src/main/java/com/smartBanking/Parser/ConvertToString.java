package main.java.com.smartBanking.Parser;

import java.util.List;

import main.java.com.smartBanking.bin.BinCondition;

public class ConvertToString {
	public static String binConditionsToString(List<BinCondition> binConditions){
		String returnOutput = "";
		for(BinCondition bin:binConditions){
			returnOutput += bin.getType()+" " + bin.getCond()+"*#";
		}
		
		return returnOutput;
	}

}
