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
			String cSQL = "SELECT * FROM rule WHERE pid = ? ORDER BY rid";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinRule> rules = new ArrayList<>();
			
			while (result.next())
			{
				BinRule binRule = new BinRule();
				
				binRule.setRID(Integer.parseInt(result.getString("rid")));
				binRule.setPID(pid);
				binRule.setRuleName(result.getString("ruleName"));
				binRule.setCondition(result.getString("condition"));
				binRule.setAction(result.getString("action"));
				
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
			String cSQL = "INSERT INTO `rule` (`pid`, `condition`, `action`, `ruleName`) VALUES (?,?,?,?)";
			
			prepStmt = connection.prepareStatement(cSQL);		
			prepStmt.setInt(1, binRule.getPID());
			prepStmt.setString(2, binRule.getCondition());
			prepStmt.setString(3, binRule.getAction());
			prepStmt.setString(4, binRule.getRuleName());

			prepStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;	
		}
	}
	

}
