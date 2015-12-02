package main.java.com.smartBanking.bin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BinAccount {
	@XmlElement
	private int accountId;
	@XmlElement
	private int pid;
	@XmlElement
	private int account_type;
	@XmlElement
	private String account_number;
	@XmlElement
	private String account_desciption;
	
	public BinAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BinAccount(int accountId, int pid, int account_type, String account_number, String account_desciption) {
		super();
		this.accountId = accountId;
		this.pid = pid;
		this.account_type = account_type;
		this.account_number = account_number;
		this.account_desciption = account_desciption;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAccount_desciption() {
		return account_desciption;
	}
	public void setAccount_desciption(String account_desciption) {
		this.account_desciption = account_desciption;
	}
	

}
