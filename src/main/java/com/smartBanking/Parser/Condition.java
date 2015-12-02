package main.java.com.smartBanking.Parser;

public class Condition {

	String field;
	String operand;
	double value;
	
	public Condition()
	{
		this.field = "";
		this.operand = "";
		this.value = 0.0;
	}
	
	public Condition(String field, String operand, double value)
	{
		this.field = field;
		this.operand = operand;
		this.value = value;
	}
	
	public Condition(String field, double value)
	{
		this.field = field;
		this.operand = "";
		this.value = value;
	}
	
	
}
