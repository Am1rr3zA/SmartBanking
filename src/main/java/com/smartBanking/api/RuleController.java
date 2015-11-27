package main.java.com.smartBanking.api;

import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.RuleDao;

@Path("/rule")
public class RuleController {

	  RuleDao dao = new RuleDao();
	  @GET
	  @Produces("application/json")
	  public Response login() throws JSONException, AuthenticationException {
		  List<BinRule> rules = dao.getRulesForUser("1");
		  
		  return Response.status(200).entity(rules.get(0).toString()).build();
	  
	  }

}
