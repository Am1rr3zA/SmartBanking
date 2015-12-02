package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import main.java.com.smartBanking.Exceptions.UserNotFound;
import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.RuleDao;
import main.java.com.smartBanking.services.BankAPI;

@Path("/rule")
public class RuleController {

//		RuleDao ruleDao = new RuleDao();
		LoginDao loginDao = new LoginDao();
		@GET
		@Produces("application/json")
		public Response bankApiTest() throws JSONException, AuthenticationException, notFound, SQLException, ParseException, UserNotFound {
			BinLogin login = loginDao.getLoginByUsername("elnaz");

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ClientID", login.getClientID());
			jsonObject.put("ClientSecret", login.getClientSecret());
			jsonObject.put("ClientAccounts", login.getAccounts());
			jsonObject.put("ClientAccessToken", login.getAccess_token());
	    
			
//			ClientResponse response = BankAPI.getBalance(login.getAccess_token(), "0100907846000");
//			ClientResponse response = BankAPI.getAccountFullStatement(login.getAccess_token(), "0100907846000", "13940901", "13940909");
//			ClientResponse response = BankAPI.getCheckInfo(login.getAccess_token(), "0100907846000", "0000396778");
//			ClientResponse response = BankAPI.getChequeBookInfo(login.getAccess_token(), "0100907846000", "4389781091");
			ClientResponse response = BankAPI.getTicketTransferLocal(login.getAccess_token(), "0100907846000", "0200217195008","50");
//			ClientResponse response = BankAPI.TransferLocal(login.getAccess_token(), "0100907846000", "0200217195008","50", "3280712952681600");
			
		    if (response.getStatus() == 401) {	        
					throw new AuthenticationException("Access Token");			
		    }
		    
		    String result = "@Produces(\"application/json\") for Elnaz with: \n " + jsonObject + "\nAccount Summary is :\n\n\n" + response.getEntity(String.class);
			
			return Response.status(200)
					.entity(result).build();
	}


}
