package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.smartBanking.bin.BinLogin;
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
	
	public List<BinRule> getRulesForUser(String pid) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM rule WHERE pid = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setString(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinRule> rules = new ArrayList<>();
			
			while (result.next())
			{
				BinRule binRule = new BinRule();
				
				binRule.setRID(result.getString(1));
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
	

}
