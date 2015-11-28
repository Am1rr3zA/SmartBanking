package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinReport;
import main.java.com.smartBanking.bin.BinRule;

public class RuleDao {
	DatabaseAccess data;
	Connection connection;
	
	public RuleDao()
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
	
	public List<BinRule> getRulesForUser(int pid) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM rule WHERE pid = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinRule> rules = new ArrayList<>();
			
			while (result.next())
			{
				BinRule binRule = new BinRule();
				
				binRule.setRID(Integer.parseInt(result.getString(1)));
				binRule.setPID(pid);				
				binRule.setCondition(result.getString(3));
				binRule.setAction(result.getString(4));
				
				rules.add(binRule);
			}
			return rules;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}
	
	public void insertRuleByUserID(BinRule binRule) throws SQLException
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "insert  into `rule`(`rid`,`pid`,`condition`,`action`) values (?,?,?,?)";
			
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, binRule.getRID());			
			prepStmt.setInt(2, binRule.getPID());
			prepStmt.setString(3, binRule.getCondition());
			prepStmt.setString(4, binRule.getAction());

			prepStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;	
		}
	}
	

}
