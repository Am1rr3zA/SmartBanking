package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.smartBanking.bin.BinReport;
import main.java.com.smartBanking.bin.BinRule;

public class ReportDao {
	DatabaseAccess data;
	Connection connection;

	public ReportDao()
	{
		try {
			data = new DatabaseAccess();
			connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void connect() throws SQLException
	{
		try
		{
			data.connect();
			connection = data.connection;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insertData(BinReport binReport) throws SQLException
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "insert  into `report`(`pid`,`rid`,`reportdate`,`satisfy`,`trigered`,`reject`) values (?,?,?,?,NULL,NULL)";
			
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, binReport.getPid());
			
			prepStmt.setInt(2, binReport.getRid());
			prepStmt.setString(3, binReport.getReportdate());
			if(binReport.isSatisfy() == false)
				prepStmt.setInt(4, 0);
			else if(binReport.isSatisfy() == true)
				prepStmt.setInt(4, 1);
			
			System.out.println(binReport.getPid());
			System.out.println(binReport.getRid());
			System.out.println(binReport.getReportdate());
			System.out.println(cSQL);
			//prepStmt.setString(5, binReport.getTriger());
			//prepStmt.setString(6, binReport.getReject());
			prepStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;	
		}
	}
	
	public List<BinReport> getAllReportForUser(int pid) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM report WHERE pid = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinReport> reports = new ArrayList<>();
			
			while (result.next())
			{
				BinReport binReport = new BinReport();
				binReport.seId(Integer.parseInt(result.getString("id")));
				binReport.setPid(pid);
				binReport.setReject(result.getString("reject"));
				binReport.setReportDate(result.getString("reportdate"));
				binReport.setRid(Integer.parseInt(result.getString("rid")));
				binReport.setSatisfy(result.getBoolean("satisfy"));
				binReport.setTriger(result.getString("trigered"));
				
				reports.add(binReport);
			}
			return reports;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}


	public List<BinReport> getAllReportForRule(int rid) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM report WHERE rid = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, rid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinReport> reports = new ArrayList<>();
			
			while (result.next())
			{
				BinReport binReport = new BinReport();
				binReport.seId(Integer.parseInt(result.getString("id")));
				binReport.setPid(Integer.parseInt(result.getString("pid")));
				binReport.setReject(result.getString("reject"));
				binReport.setReportDate(result.getString("reportdate"));
				binReport.setRid(rid);
				binReport.setSatisfy(result.getBoolean("satisfy"));
				binReport.setTriger(result.getString("trigered"));
				
				reports.add(binReport);
			}
			return reports;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}



}
