package main.java.com.smartBanking.bin;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BinCondition {

	String type;
	String cond;
	
	public BinCondition(String type, String cond) {
		this.type = type;
		this.cond = cond;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}
	


}
