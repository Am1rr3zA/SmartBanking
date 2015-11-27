package main.java.com.smartBanking.bin;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InputData {
	String PID;
	String RID;
	String action;
	String condition;
	
	public InputData(String PID, String RID, String Action, String Condition)
	{
		this.PID = PID;
		this.RID = RID;
		this.action = Action;
		this.condition = Condition;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getRID() {
		return RID;
	}

	public void setRID(String rID) {
		RID = rID;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	
}
