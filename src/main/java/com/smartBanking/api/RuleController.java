package main.java.com.smartBanking.api;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.Parser.ConvertToString;
import main.java.com.smartBanking.Parser.Parse;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinReport;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.da.ReportDao;
import main.java.com.smartBanking.da.RuleDao;

@Path("/rule")
public class RuleController {

	RuleDao dao = new RuleDao();
	LoginDao loginDao = new LoginDao();
	ReportDao reportDao = new ReportDao();
	@GET
	@Produces("application/json")
	public Response login() throws JSONException, AuthenticationException, notFound, SQLException {
		int pid = 1;
		List<BinRule> rules = dao.getRulesForUser(pid);
		
		BinLogin user = loginDao.getLoginByID(1);

		generateReportData(rules, user);

		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
			    .entity(rules.get(0).toString()).build();
	}

	public void generateReportData(List<BinRule> rules,BinLogin user) throws notFound, SQLException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:MM:SS");
		Date date = new Date();
		
		for(BinRule rule:rules)
		{
			Parse parser = new Parse(rule,user);
			//System.out.println("........"+rule.getRID());
			System.out.println("condition in controler "+rule.getCondition());
//			This How you should get the conditions
//			List<BinCondition> tmp = ConvertToString.stringToBinConditions(rules.get(0).getCondition());
			List<Boolean> result = parser.feasibility(parser.parseCondition());
			boolean flagOneFalse = false, flagOneTrue = false;
			for(boolean value:result)
			{
				if(value == true)
					flagOneTrue = true;
				if(value == false)
					flagOneFalse = true;
			}

			System.out.println("flagOneTrue "+flagOneTrue+" flagFalse "+flagOneFalse);
			if(flagOneTrue == true && flagOneFalse == false)
			{
				
				BinReport reportSample = new BinReport(rule.getPID(),rule.getRID(), date.toString() , true, null, null);
				reportDao.insertData(reportSample);
				//all are true
			}
			else if(flagOneTrue == true && flagOneFalse == true)
			{
				BinReport reportSample = new BinReport(rule.getPID(), rule.getRID(), date.toString() , false, null, null);
				reportDao.insertData(reportSample);
				
				//reportDao.insertData(rule.getPID(), date , false, null, null);
				//atleast one true and one false
			}
			else if(flagOneTrue == false && flagOneFalse == true )
			{
				System.out.println("no condition is true");
				//reportDao.insertData(rule.getPID(), date , false, null, null);
				//all are false
			}
		}
	System.out.println("end of generation");	
	}

}
