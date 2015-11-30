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

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.Logic.Parse;
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
		public Response login() throws JSONException, AuthenticationException, notFound, SQLException, ParseException {
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
	
	public void generateReportData(List<BinRule> rules,BinLogin user) throws notFound, SQLException, ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:MM:SS");
		Date date = new Date();

		for(BinRule rule:rules)
		{
			Parse parser = new Parse(rule,user);
			//System.out.println("........"+rule.getRID());
			//System.out.println("condition in controler "+rule.getCondition());
			List<BinCondition> condition = ConvertToString.stringToBinConditions(rule.getCondition());
			//System.out.println("before parser");
			List<Boolean> result = parser.feasibility(condition);

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
		System.out.println("end of generation");	
	}

}
