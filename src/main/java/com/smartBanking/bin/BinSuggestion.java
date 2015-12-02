package main.java.com.smartBanking.bin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BinSuggestion {
	@XmlElement
	int id;
	@XmlElement
	int pid;
	@XmlElement
	String account_number;
	@XmlElement
	String suggestion_name;
	
	public BinSuggestion(int id, int pid, String account_number, String suggestion_name) {
		super();
		this.id = id;
		this.pid = pid;
		this.account_number = account_number;
		this.suggestion_name = suggestion_name;
	}
	public BinSuggestion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getSuggestion_name() {
		return suggestion_name;
	}
	public void setSuggestion_name(String suggestion_name) {
		this.suggestion_name = suggestion_name;
	}

}
