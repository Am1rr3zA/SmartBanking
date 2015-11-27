package main.java.com.smartBanking.bin;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement
public class BinRule {
	@XmlElement
	String PID;
	@XmlAttribute
	String RID;
	@XmlElement
	String action;
	@XmlElement
	String condition;

	public BinRule()
	{
	
	}
	
	public BinRule(String PID, String RID, String Action, String Condition)
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
