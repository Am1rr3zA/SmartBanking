package main.java.com.smartBanking.da;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseAccess {
	Connection connection = null;

	public void connect() throws SQLException
	{
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://127.0.0.1/smartBanking?";
		String UserName = "root";
		String Password = "root";
		try
		{
			Class.forName(DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			connection = DriverManager.getConnection(URL,UserName,Password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void disconnect() throws SQLException
	{
		connection.close();
	}
}