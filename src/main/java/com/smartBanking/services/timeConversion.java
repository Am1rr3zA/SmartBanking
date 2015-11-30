package main.java.com.smartBanking.services;

import java.util.Date;

import com.bahmanm.persianutils.DateConverter;

import main.java.com.smartBanking.bin.BinCondition;

public class timeConversion {
	
	public String GregorianToJalali(String time)
	{
		String output = "";
		DateConverter.SimpleDate gd2 = new DateConverter.SimpleDate(Integer.valueOf(time.substring(0, 4)), Integer.valueOf(time.substring(4,6)),Integer.valueOf(time.substring(6)));
		DateConverter.SimpleDate pd2 = DateConverter.gregorianToPersian(gd2);
		  
		String[] tempDate = pd2.toString().split("/");
		for(int i=0; i<tempDate.length; i++)
		{
			if(tempDate[i].length()>1)
				output+=tempDate[i].toString();
			else
				output=output+"0"+tempDate[i].toString();
		}
		return output;
	}
	

	public String jalaliTogregorian(String time)
	{
		
		DateConverter.SimpleDate pd1 = new DateConverter.SimpleDate(Integer.valueOf(time.substring(0, 4)),Integer.valueOf(time.substring(4, 6)), Integer.valueOf(time.substring(6)));
	    DateConverter.SimpleDate gd1 = DateConverter.persianToGregorian(pd1);
	    
	    
		String bintime = gd1.toString();
		String[] binDate = bintime.toString().split("/");
		bintime = "";
		for(int i=0; i<binDate.length; i++)
		{
			if(binDate[i].length()>1)
				bintime+=binDate[i].toString();
			else
				bintime=bintime+"0"+binDate[i].toString();
		}
return bintime;

	}

	
	public String jalaliTogregorian(BinCondition bincondition)
	{
		
		DateConverter.SimpleDate pd1 = new DateConverter.SimpleDate(Integer.valueOf(bincondition.getCond().substring(0, 4)),Integer.valueOf(bincondition.getCond().substring(4, 6)), Integer.valueOf(bincondition.getCond().substring(6)));
	    DateConverter.SimpleDate gd1 = DateConverter.persianToGregorian(pd1);
	    
	    
		String bintime = gd1.toString();
		String[] binDate = bintime.toString().split("/");
		bintime = "";
		for(int i=0; i<binDate.length; i++)
		{
			if(binDate[i].length()>1)
				bintime+=binDate[i].toString();
			else
				bintime=bintime+"0"+binDate[i].toString();
		}
return bintime;

	}
	
	
	
	public String[] jalaliTogregorian(BinCondition bincondition, String delim)
	{
		String[] output = new String[2];
		String[] dates = bincondition.getCond().split(delim);
		DateConverter.SimpleDate pd1 = new DateConverter.SimpleDate(Integer.valueOf(dates[0].substring(0, 4)),Integer.valueOf(dates[0].substring(4, 6)), Integer.valueOf(dates[0].substring(6)));
	    DateConverter.SimpleDate gd1 = DateConverter.persianToGregorian(pd1);
	    
	    DateConverter.SimpleDate pd2 = new DateConverter.SimpleDate(Integer.valueOf(dates[1].substring(0, 4)),Integer.valueOf(dates[1].substring(4, 6)), Integer.valueOf(dates[1].substring(6)));
	    DateConverter.SimpleDate gd2 = DateConverter.persianToGregorian(pd2);
	    
	    
		String bintime = gd1.toString();
		String[] binDate = bintime.toString().split("/");
		bintime = "";
		for(int i=0; i<binDate.length; i++)
		{
			if(binDate[i].length()>1)
				bintime+=binDate[i].toString();
			else
				bintime=bintime+"0"+binDate[i].toString();
		}
		
		String bintime2 = gd2.toString();
		String[] binDate2 = bintime2.toString().split("/");
		bintime2 = "";
		for(int i=0; i<binDate2.length; i++)
		{
			if(binDate2[i].length()>1)
				bintime2+=binDate2[i].toString();
			else
				bintime2=bintime2+"0"+binDate2[i].toString();
		}
		
		System.out.println("first "+bintime);
		System.out.println("second "+bintime2);
		
		output[0] = bintime;
		output[1] = bintime2;
return output;

	}
		
	
}
