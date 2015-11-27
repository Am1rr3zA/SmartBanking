package main.java.com.smartBanking.bin;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
public class BinRule {
	@XmlElement
	int PID;
	@XmlAttribute
	int RID;
	@XmlElement
	String action;
	@XmlElement
	String condition;

	public BinRule()
	{
	
	}
	
	public BinRule(int PID, int RID, String Action, String Condition)
	{
		this.PID = PID;
		this.RID = RID;
		this.action = Action;
		this.condition = Condition;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getRID() {
		return RID;
	}

	public void setRID(int rID) {
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

	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ruleID", getRID());
		jsonObject.put("personID", getPID());
		jsonObject.put("condition", getCondition());
		jsonObject.put("action", getAction());
		
		return jsonObject.toString();
	}
	
	
	
	
	
}
