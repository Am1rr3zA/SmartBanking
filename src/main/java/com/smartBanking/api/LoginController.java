package main.java.com.smartBanking.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/login")
public class LoginController {
	
	  @GET
	  @Produces("application/json")
	  public Response login() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("LOGIN", "Yaaaay"); 

		String result = "@Produces(\"application/json\") " + jsonObject;
		return Response.status(200).entity(result).build();
	  }

}
