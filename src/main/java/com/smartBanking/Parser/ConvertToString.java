package main.java.com.smartBanking.Parser;

import java.util.ArrayList;
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
	
	public static List<BinCondition> stringToBinConditions(String string){
		List<BinCondition> returnOutput = new ArrayList<>();
		String[] tokens = string.split("\\*#");
		for(String str:tokens){			
			BinCondition binCondition = new BinCondition(str.split(" ")[0], str.split(" ")[1]);
			returnOutput.add(binCondition);
		}
		
		
		return returnOutput;
	}

}
