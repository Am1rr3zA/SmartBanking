package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.smartBanking.Exceptions.UserNotFound;
import main.java.com.smartBanking.bin.BinLogin;

public class LoginDao {
	DatabaseAccess data;
	Connection connection;
	
	public LoginDao()
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
	
	public BinLogin getLoginByUsername(String username) throws UserNotFound 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM login WHERE username = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setString(1, username); 
			ResultSet result = prepStmt.executeQuery();
			BinLogin login = new BinLogin();
			if (result.next())
			{
				login.setLoginId(Integer.parseInt(result.getString("id")));
				login.setUsername(username);
				login.setPassword(result.getString("password"));
				login.setClientID(result.getString("client_id"));
				login.setClientSecret(result.getString("client_secret"));				
				login.setAccess_token(result.getString("access_token"));
				String tmp = result.getString("accounts");
				List<String> accounts = new ArrayList<>();
				if (tmp != null){
					accounts = Arrays.asList(tmp.split("\\s*,\\s*"));
				}
				login.setAccounts(accounts);
			}
			else{
				throw new UserNotFound("User with username = " +username +" does not exist in database");
			}
			return login;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}
	
	public BinLogin getLoginByID(int id) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM login WHERE id = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, id); 
			
			ResultSet result = prepStmt.executeQuery();
			BinLogin login = new BinLogin();
			while (result.next())
			{
				login.setLoginId(id); 
				login.setUsername(result.getString("username"));
				login.setPassword(result.getString("password"));
				login.setClientID(result.getString("client_id"));
				login.setClientSecret(result.getString("client_secret"));				
				login.setAccess_token(result.getString("access_token"));
				String tmp = result.getString("accounts");
				List<String> accounts = new ArrayList<>();
				if (tmp != null){
					accounts = Arrays.asList(tmp.split("\\s*,\\s*"));
				}
				login.setAccounts(accounts);
			}
			return login;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}
	
	
}


