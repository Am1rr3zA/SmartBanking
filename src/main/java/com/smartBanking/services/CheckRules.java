package main.java.com.smartBanking.services;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.Logic.CoreModule;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinReport;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.ReportDao;
import main.java.com.smartBanking.da.RuleDao;

public class CheckRules implements Runnable {
	RuleDao dao = new RuleDao();
	LoginDao loginDao = new LoginDao();
	ReportDao reportDao = new ReportDao();
	
	@Override
    public void run() {
		try {
			login();
		} catch (AuthenticationException | JSONException | notFound | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@GET
	@Produces("application/json")
	public Response login() throws JSONException, AuthenticationException, notFound, SQLException, ParseException {
		int pid = 1;
		List<BinRule> rules = dao.getRulesForUser(pid);
		BinLogin user = loginDao.getLoginByID(1);
		generateReportData(rules, user);

	generateReportData(rules, user);

	return Response.status(200)
		    .entity(rules.get(0).toString()).build();
}
	
	public void generateReportData(List<BinRule> rules,BinLogin user) throws notFound, SQLException, ParseException

	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:MM:SS");
		Date date = new Date();

		for(BinRule rule:rules)
		{
			CoreModule parser = new CoreModule(rule,user);
			List<BinCondition> condition = ConvertToString.stringToBinConditions(rule.getCondition());
			List<Boolean> result = parser.feasibility(condition);

			String[] action = rule.getAction().split(" ");
			
			boolean flagOneFalse = false, flagOneTrue = false;
			if(result.size()>1){
				for(boolean value:result)
				{
					
					if(value == true)
						flagOneTrue = true;
					if(value == false)
						flagOneFalse = true;
				}
			}
			else{
				if(result.get(0) == true)
				{
					flagOneTrue = true;
					flagOneFalse = false;
				}
				else
				{
					flagOneTrue = false;
					flagOneFalse = true;
				}
			}
			
			if(flagOneTrue == true && flagOneFalse == false)
			{
				
				BinReport reportSample = new BinReport(rule.getPID(),rule.getRID(), date.toString() , true, null, null);
				reportDao.insertData(reportSample);
				
				ClientResponse response = BankAPI.getTicketTransferLocal(user.getAccess_token(), user.getAccounts().get(0), action[0], action[1]);
				JSONObject jObj =  new JSONObject(response.getEntity(String.class));
				JSONObject additional = jObj.getJSONObject("additionalData");
				String ticket = additional.getString("ticket");
				BankAPI.TransferLocal(user.getAccess_token(), user.getAccounts().get(0), action[0], action[1],ticket);
				
			}
			else if(flagOneTrue == true && flagOneFalse == true)
			{
				
				BinReport reportSample = new BinReport(rule.getPID(), rule.getRID(), date.toString() , false, null, null);
				reportDao.insertData(reportSample);
			}
			else if(flagOneTrue == false && flagOneFalse == true )
			{
				
				System.out.println("no condition is true");	
			}
		}
		//System.out.println("end of generation");	
	}



}
