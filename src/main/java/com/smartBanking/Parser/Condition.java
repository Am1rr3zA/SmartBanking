package main.java.com.smartBanking.Parser;

public class Condition {

	String field;
	double value;
	
	public Condition()
	{
		this.field = "";
		
		this.value = 0.0;
	}
	
	public Condition(String field, double value)
	{
		this.field = field;
		
		this.value = value;
	}
	
	
}
