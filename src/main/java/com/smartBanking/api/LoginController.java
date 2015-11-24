package main.java.com.smartBanking.api;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import main.java.com.smartBanking.bin.Login;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.services.BankAPI;


@Path("/login")
public class LoginController {
	
	  LoginDao dao = new LoginDao();
	  @GET
	  @Produces("application/json")
	  public Response login() throws JSONException, AuthenticationException {
		  
		Login login = dao.getLoginByUsername("Elnaz");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Client ID", login.getClientID());
		jsonObject.put("Client Secret", login.getClientSecret());
		jsonObject.put("Client Activation Code", login.getActivation_code());
		jsonObject.put("Client Access Token", login.getAccess_token());

//		Client client = Client.create();
//	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/account/balance/0100907846000");
//	    ClientResponse response = webResource     
//	    		.header("Authorization", "Bearer "+login.getAccess_token())
//	    		.header("Content-Type", "application/json")
//	    		.accept(MediaType.APPLICATION_JSON)
//	            .type(MediaType.APPLICATION_JSON)
//	            .get(ClientResponse.class);
//	    
		ClientResponse response = BankAPI.getBalance(login.getAccess_token());
	    if (response.getStatus() == 401) {	        
				throw new AuthenticationException("Access Token");			
	    }
	    
	    String result = "@Produces(\"application/json\") for Elnaz with: \n " + jsonObject + "\nAccount Summary is :" + response.getEntity(String.class);
		
		return Response.status(200).entity(result).build();
	  }

}
