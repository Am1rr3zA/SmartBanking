package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.smartBanking.bin.Login;

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
	
	public Login getLoginByUsername(String username) 
	{
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM login WHERE username = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setString(1, username); 
			ResultSet result = prepStmt.executeQuery();
			Login login = new Login();
			while (result.next())
			{
				login.setClientID(result.getString(4));
				login.setClientSecret(result.getString(5));
				login.setActivation_code(result.getString(6));
				login.setAccess_token(result.getString(7));
			}
			return login;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
	}
	

	
	
}


