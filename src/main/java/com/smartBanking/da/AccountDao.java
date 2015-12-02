package main.java.com.smartBanking.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.smartBanking.Exceptions.UserNotFound;
import main.java.com.smartBanking.bin.BinAccount;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;

public class AccountDao {
	DatabaseAccess data;
	Connection connection;
	
	public AccountDao()
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
	
	public List<BinAccount> getAccountsById(int pid){
		PreparedStatement prepStmt = null;
		try {
			String cSQL = "SELECT * FROM account WHERE pid = ? ";
			prepStmt = connection.prepareStatement(cSQL);
			prepStmt.setInt(1, pid); 
			ResultSet result = prepStmt.executeQuery();
			
			List<BinAccount> accounts = new ArrayList<>();
			while (result.next())
			{
				BinAccount account = new BinAccount();
				account.setAccount_desciption(result.getString("description"));
				account.setAccount_number(result.getString("account_number"));
				account.setAccount_type(result.getInt("account_type"));
				account.setAccountId(result.getInt("id"));
				account.setPid(pid);
				accounts.add(account);
			}
		
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			prepStmt = null;
			return null;
		}
		
	}
	
	public void insertAccountsByUserID(List<BinAccount> binAccounts) throws SQLException
	{
		for (BinAccount binAccount: binAccounts){
			PreparedStatement prepStmt = null;
			try {
				String cSQL = "INSERT INTO `account` (`pid`, `account_number`, `account_type`, `description`) VALUES (?,?,?,?)";
				
				prepStmt = connection.prepareStatement(cSQL);		
				prepStmt.setInt(1, binAccount.getPid());
				prepStmt.setString(2,binAccount.getAccount_number());
				prepStmt.setInt(3, binAccount.getAccount_type());
				prepStmt.setString(4, binAccount.getAccount_desciption());
	
				prepStmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
				prepStmt = null;	
			}
		}
	}
	

}
