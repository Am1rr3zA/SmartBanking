package main.java.com.smartBanking.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BankAPI {

	
	
	public static ClientResponse getBalance(String access_token)
	{
		Client client = Client.create();
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/account/balance/0100907846000");
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer "+access_token)
	    		.header("Content-Type", "application/json")
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	return response;
	}
	
	
}
