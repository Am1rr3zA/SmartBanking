package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.com.smartBanking.bin.BinAccount;
import main.java.com.smartBanking.da.AccountDao;

@Path("/account")
public class AccountController {
	AccountDao accountDao = new AccountDao();
	
	@GET
	@Produces("application/json")
	public Response getAllAccounts(@DefaultValue("1") @QueryParam("pid") String pid){
		List<BinAccount> binAccounts = new ArrayList<>();
		binAccounts = accountDao.getAccountsById(Integer.parseInt(pid));
		
		JSONArray resp = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		for (BinAccount binAccount:binAccounts){
			jsonObject = new JSONObject();
			jsonObject.put("AccountDescription", binAccount.getAccount_desciption());
			jsonObject.put("AccountID", binAccount.getAccountId());
			jsonObject.put("PID", binAccount.getPid());
			jsonObject.put("Accounttype", binAccount.getAccount_type());
			jsonObject.put("AccountNumber", binAccount.getAccount_number());
			
			resp.put(jsonObject);			
		}
		
		 String result = resp.toString();
			
		return Response.status(200).entity(result).build();
		
	}
	
	 @POST
	 @Produces("application/json")
	 public Response insertAccounts(String in_json) throws SQLException{
		 
		 JSONArray input_json = new JSONArray(in_json);
		 
		 List<BinAccount> binAccounts = new ArrayList<>();
		 
		 for (int i = 0; i < input_json.length(); ++i) {
			 BinAccount binAccount = new BinAccount();
			 
			 JSONObject jsonObject = input_json.getJSONObject(i);
			 binAccount.setAccount_desciption(jsonObject.getString("AccountDescription"));
			 binAccount.setAccount_number(jsonObject.getString("AccountNumber"));
			 binAccount.setAccount_type(jsonObject.getInt("Accounttype"));
			 binAccount.setPid(jsonObject.getInt("PID"));
			 
			 binAccounts.add(binAccount);
		 }
		 
		 accountDao.insertAccountsByUserID(binAccounts);
		 
		 return Response.status(200)
				    .entity(in_json)			    
				    .build();		 
	 }

}
