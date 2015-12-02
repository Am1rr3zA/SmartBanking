package main.java.com.smartBanking.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.com.smartBanking.bin.BinAccount;
import main.java.com.smartBanking.bin.BinSuggestion;
import main.java.com.smartBanking.da.AccountDao;
import main.java.com.smartBanking.da.SuggestionDao;

@Path("/suggestion")
public class SuggestionController {
	
	SuggestionDao suggestionDao = new SuggestionDao();
	
	@GET
	@Produces("application/json")
	public Response getSuggestion(@DefaultValue("1") @QueryParam("pid") String pid){
		List<BinSuggestion> binSuggestions = new ArrayList<>();
		binSuggestions = suggestionDao.getSuggestionForUser(Integer.parseInt(pid));
		
		JSONArray resp = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		for (BinSuggestion binSuggestion:binSuggestions){
			jsonObject = new JSONObject();
			jsonObject.put("Sugestion", binSuggestion.getSuggestion_name());
			jsonObject.put("AccountID", binSuggestion.getId());
			jsonObject.put("PID", binSuggestion.getPid());
			jsonObject.put("AccountNumber", binSuggestion.getAccount_number());
			
			resp.put(jsonObject);			
		}
		
		 String result = resp.toString();
			
		return Response.status(200).entity(result).build();
		
	}
}
