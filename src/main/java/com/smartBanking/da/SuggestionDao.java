package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.bin.BinSuggestion;

public class SuggestionDao {
	DatabaseAccess data;
	Connection connection;
	
	public SuggestionDao()
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
	
	public List<BinSuggestion> getSuggestionForUser(int pid) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM suggestion WHERE pid = ?";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			List<BinSuggestion> suggestions = new ArrayList<>();
			
			while (result.next())
			{
				BinSuggestion binSuggestion = new BinSuggestion();
				
				binSuggestion.setAccount_number(result.getString("account_number"));
				binSuggestion.setId(result.getInt("id"));
				binSuggestion.setPid(pid);
				binSuggestion.setSuggestion_name(result.getString("suggestion_name"));
				
				
				suggestions.add(binSuggestion);
			}
			return suggestions;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}
}
