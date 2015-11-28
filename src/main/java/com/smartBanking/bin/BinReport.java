package main.java.com.smartBanking.bin;



import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BinReport {
	public BinReport(int pid, int rid ,String reportdate, boolean satisfy, String triger, String reject) {
		super();
		
		this.rid = rid;
		this.pid = pid;
		this.reportdate = reportdate;
		this.satisfy = satisfy;
		this.triger = triger;
		this.reject = reject;
		//this.id = id;
	}
	@XmlElement
	private int id;
	@XmlElement
	private int rid;
	@XmlElement
	private int pid;
	@XmlElement
	private String reportdate;
	@XmlElement
	private boolean satisfy;
	@XmlElement
	private String triger;
	@XmlAttribute
	private String reject;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getId() {
		return id;
	}
	public void seId(int id) {
		this.id = id;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getReportdate() {
		return reportdate;
	}
	public void setReportDate(String reportdate) {
		this.reportdate = reportdate;
	}
	public boolean isSatisfy() {
		return satisfy;
	}
	public void setSatisfy(boolean satisfy) {
		this.satisfy = satisfy;
	}
	public String getTriger() {
		return triger;
	}
	public void setTriger(String triger) {
		this.triger = triger;
	}
	public String getReject() {
		return reject;
	}
	public void setReject(String reject) {
		this.reject = reject;
	}
	
	
}
