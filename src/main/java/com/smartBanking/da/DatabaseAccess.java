package main.java.com.smartBanking.da;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseAccess {
	Connection connection = null;

	public void connect() throws SQLException
	{
		String DRIVER = "com.mysql.jdbc.Driver";
	//	String URL = "jdbc:mysql://127.0.0.1/smartBanking?";
//		String URL = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/smart?";
	
		String URL = "jdbc:mysql://127.13.80.130:3306/smart?useUnicode=true&characterEncoding=utf-8";
		
		String UserName = "adminEjJda5P";
		String Password = "_PNzUnS4LySk";
		
		//String UserName = "root";
		//String Password = "";
		
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
