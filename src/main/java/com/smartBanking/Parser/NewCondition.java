package main.java.com.smartBanking.Parser;

public class NewCondition extends Condition {

	boolean satisfy;//true if satisfied, false if atleast one condition didn't satisfy
	public NewCondition()
	{
		super();
		satisfy = false;
	}
	
	public NewCondition(String field, String operand, double value, boolean satisfy)
	{
		super(field, operand, value);
		this.satisfy = satisfy;
	}
	
	public NewCondition(String field, double value, boolean satisfy)
	{
		super(field, value);
		this.satisfy = satisfy;
	}
	
	
}
