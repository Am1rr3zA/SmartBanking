package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.RuleDao;
import main.java.com.smartBanking.services.BankAPI;

@Path("/login")
public class LoginController {
	
	  LoginDao dao = new LoginDao();
	  RuleDao ruleDao = new RuleDao();
	  @GET
	  @Produces("application/json")
	  public Response login() throws JSONException, AuthenticationException {
		  
		BinLogin login = dao.getLoginByUsername("Elnaz");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Client ID", login.getClientID());
		jsonObject.put("Client Secret", login.getClientSecret());
		jsonObject.put("Client Accounts", login.getAccounts());
		jsonObject.put("Client Access Token", login.getAccess_token());
    
		
//		ClientResponse response = BankAPI.getBalance(login.getAccess_token(), "0100907846000");
//		ClientResponse response = BankAPI.getAccountFullStatement(login.getAccess_token(), "0100907846000", "13940901", "13940909");
//		ClientResponse response = BankAPI.getCheckInfo(login.getAccess_token(), "0100907846000", "0000396778");
//		ClientResponse response = BankAPI.getChequeBookInfo(login.getAccess_token(), "0100907846000", "4389781091");
		ClientResponse response = BankAPI.getTicketTransferLocal(login.getAccess_token(), "0100907846000", "0200217195008","50");
//		ClientResponse response = BankAPI.TransferLocal(login.getAccess_token(), "0100907846000", "0200217195008","50", "3280712952681600");
		
	    if (response.getStatus() == 401) {	        
				throw new AuthenticationException("Access Token");			
	    }
	    
	    String result = "@Produces(\"application/json\") for Elnaz with: \n " + jsonObject + "\nAccount Summary is :\n\n\n" + response.getEntity(String.class);
		
		return Response.status(200)
			    .entity(result).build();
	  }
	  
	 
	  @POST
	  @Produces("application/json")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response loginPost(String in_json) throws JSONException, AuthenticationException, SQLException {
		  
		BinRule newRule = new BinRule();  
		
		JSONObject jsonObj = new JSONObject(in_json);
		
		String pid = jsonObj.getString("pid");
		newRule.setPID(Integer.parseInt(pid));
		
		String ruleName = jsonObj.getString("name"); 
		newRule.setRuleName(ruleName);
		
		List<BinCondition> binCondintions = new ArrayList<>();
		
		JSONArray conditions = jsonObj.getJSONArray("condition");		
		for (int i = 0; i < conditions.length(); i++) {			
		    JSONObject condition = conditions.getJSONObject(i); 
		    String type = String.valueOf(condition.getInt("type"));
		    String value = condition.getString("cond");   
		    BinCondition binCondintion = new BinCondition(type, value);
		    binCondintions.add(binCondintion);
		}
		
		newRule.setCondition(ConvertToString.binConditionsToString(binCondintions));
		
		JSONObject action = jsonObj.getJSONObject("action");
		String account = action.getString("destination");
		int amount = action.getInt("value");
		newRule.setAction(account+" "+String.valueOf(amount));
		
		ruleDao.insertRuleByUserID(newRule);
	   
		
		return Response.status(200)
			    .entity(in_json).build();
	  }

}
