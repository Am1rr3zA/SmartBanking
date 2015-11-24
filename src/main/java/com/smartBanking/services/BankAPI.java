package main.java.com.smartBanking.services;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BankAPI {

	
	
	public static ClientResponse getBalance(String access_token, String account_number)
	{
		Client client = Client.create();
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/account/balance/" + account_number);
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer " + access_token)
	    		.header("Content-Type", "application/json")
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	    return response;
	}
	
	public static ClientResponse getAccountShortStatement(String access_token, String account_number)
	{
		Client client = Client.create();
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/account/statement/mini/" + account_number);
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer " + access_token)
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	    return response;
	}
	
	public static ClientResponse getAccountFullStatement(String access_token, String account_number, String from, String to)
	{
		Client client = Client.create();
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/account/statement/full/"+account_number+"/"+from+"/"+to);
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer " + access_token)
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	    return response;
	}
	
	public static ClientResponse getCheckInfo(String access_token, String account_number, String cheque)
	{
		Client client = Client.create();            
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/cheque/info/"+account_number+"/"+cheque);
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer " + access_token)
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	    return response;
	}
	
	public static ClientResponse getChequeBookInfo(String access_token, String account_number, String cheque)
	{
		Client client = Client.create();            
	    WebResource webResource =   client.resource("http://obg.in-bank.ir/apibank/api/v0/cheque/book/info/"+account_number+"/"+cheque);
	    ClientResponse response = webResource     
	    		.header("Authorization", "Bearer " + access_token)
	    		.accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON)
	            .get(ClientResponse.class);
	    
	    return response;
	}
	
	
	
	
}
