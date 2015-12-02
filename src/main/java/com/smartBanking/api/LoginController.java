package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import main.java.com.smartBanking.Exceptions.UserNotFound;
import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.RuleDao;
import main.java.com.smartBanking.services.BankAPI;


@Path("/login")
public class LoginController {
	
	  LoginDao loginDao = new LoginDao();
	  RuleDao ruleDao = new RuleDao();
	  @GET
	  @Produces("application/json")
	  public Response login(@DefaultValue("Elnaz") @QueryParam("username") String username) throws JSONException, AuthenticationException {
		JSONObject loginJson = new JSONObject();
		try {
			BinLogin binLogin = loginDao.getLoginByUsername(username);
			loginJson.put("status", "OK");
			
			JSONObject loginStatus = new JSONObject();
			loginStatus.put("pid", binLogin.getLoginId());
			loginStatus.put("AccessToken", binLogin.getAccess_token());
			loginStatus.put("ClientID", binLogin.getClientID());
			loginStatus.put("ClientSecret", binLogin.getClientSecret());
			loginStatus.put("Accounts", binLogin.getAccounts());
			
			loginJson.put("Additional Data", loginStatus);
		} catch (UserNotFound e) {
			loginJson.put("status", "NOK");
			loginJson.put("Additional Data", "User Not Found");
			
		}
		
		return Response.status(200)
				.entity(loginJson.toString()).build();
	  }
	  

	 
	  @POST
	  @Produces("application/json")
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
			    .entity(in_json)			    
			    .build();

	  }

}
