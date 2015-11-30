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
//		String URL = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/smart?";
		
//		String URL = "jdbc:mysql://localhost:3307/am1rr3za?autoReconnect=true";
		
//		String UserName = "adminEjJda5P";
//		String Password = "_PNzUnS4LySk";
		
		String UserName = "root";
		String Password = "";
		
//		String UserName = "am1rr3za";
//		String Password = "Amirreza123";
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