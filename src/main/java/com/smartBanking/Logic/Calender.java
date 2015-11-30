package main.java.com.smartBanking.Logic;

public class Calender {
	int day;
	int month; 
	int year;

	public Calender(String date)
	{
		day = Integer.valueOf(date.substring(6));
		month = Integer.valueOf(date.substring(4, 6));
		year = Integer.valueOf(date.substring(0,4));
	}

	public String Yesterday(Calender cal)
	{
		if(cal.day>=2)
		{

			int newDay = cal.day-1;
			if(newDay<10 && month>9)
				return String.valueOf(year)+String.valueOf(month)+"0"+String.valueOf(newDay);
			else if(newDay<10 && month<10)
				return String.valueOf(year)+"0"+String.valueOf(month)+"0"+String.valueOf(newDay);
			else if (newDay>9 && month<10)
				return String.valueOf(year)+"0"+String.valueOf(month)+String.valueOf(newDay);
			else
				return String.valueOf(year)+String.valueOf(month)+String.valueOf(newDay);
		}
		else if(cal.day == 1)
		{
			if(month>= 2 && month <= 6)
				return String.valueOf(year)+"0"+String.valueOf(month-1)+String.valueOf(31);
			else if(month == 1)
				return String.valueOf(year-1)+String.valueOf(12)+String.valueOf(29);
			else if(month>7 && month<=12)
			{
				if(month>9)
					return String.valueOf(year)+String.valueOf(month-1)+String.valueOf(30);
				else if(month<10)
					return String.valueOf(year)+"0"+String.valueOf(month-1)+String.valueOf(30);
			}
				
		}
		return null;
	}



}
