package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.RuleDao;

@Path("/list")
public class ListController {
	RuleDao dao = new RuleDao();
	
	@GET
	@Produces("application/json")
	public Response returnAllRulesForSpecificUser() throws JSONException, AuthenticationException, notFound, SQLException {
		int pid = 1;
		List<BinRule> rules = dao.getRulesForUser(pid);
		JSONArray resp = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		for (BinRule rule:rules){
			jsonObject = new JSONObject();
			
			jsonObject.put("rid", rule.getRID());
			jsonObject.put("pid", rule.getPID());
			jsonObject.put("name", rule.getRuleName());
			JSONObject actiobObject = new JSONObject();
			String[] parts = rule.getAction().split("\\s+");
			actiobObject.put("destination", parts[0]);
			actiobObject.put("value", parts[1]);
			jsonObject.put("action", actiobObject);
			
			
			String rul = rule.getCondition();
			JSONArray rulArray = new JSONArray();
			JSONObject rulObject = new JSONObject();
			if (rul != null){
				List<BinCondition> trigConds = ConvertToString.stringToBinConditions(rul);
				for (BinCondition trigCond:trigConds){
					rulObject.put("type".trim(), trigCond.getType().trim());
					rulObject.put("cond".trim(), trigCond.getCond().trim());
					rulArray.put(rulObject);
				}
//				rul = rulArray.toString();
			}
			jsonObject.put("condition", rulArray);
			
			resp.put(jsonObject);
		}
		
	    String result = resp.toString();
		
		return Response.status(200)
			    .entity(result).build();
	
	}

}
