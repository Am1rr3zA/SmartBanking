package main.java.com.smartBanking.Parser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;

import org.json.JSONObject;
import org.json.JSONStringer;

import com.sun.jersey.api.client.ClientResponse;

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.api.LoginController;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.services.BankAPI;

public class Parse {

	//String Input_condition;
	//String Input_action;
	String token;
	String accountNum;
	LoginDao dao = new LoginDao();




	public Parse(BinRule input, BinLogin user) throws notFound
	{
		//Input_condition = input.getCondition();
		//Input_action = input.getAction();
		if(user.getAccess_token()!= null)
			token = user.getAccess_token();
		else
			throw new notFound("Not found access-token");

		if(user.getAccounts()!= null)
			accountNum = user.getAccounts().get(0);
		else
			throw new notFound("Not found account number");
	}

	


	public List<Boolean> feasibility(List<Condition> conditions)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd,HH");
		//get current date time with Date()
		Date date = new Date();
		String[] systemDate = dateFormat.format(date).toString().split(",");


		List<Boolean> feasValues = new ArrayList<>();
		for(Condition cond: conditions.)
		{
			String accBalance="";
			if(cond.field.contains("balance"))
			{

				ClientResponse response = BankAPI.getBalance(token, accountNum);
				JSONObject jObject  = new JSONObject(response.getEntity(String.class));
				accBalance = jObject.get("additionalData").toString();
			}
			
			if(cond.field.equals("balance>"))
			{
				if(cond.value <= Double.valueOf(accBalance))
					feasValues.add(false);
				else
					feasValues.add(true);
			}


			if(cond.field.equals("balance<"))
			{
				if(cond.value >= Double.valueOf(accBalance))
					feasValues.add(false);
				else 
					feasValues.add(true);
			}

			if(cond.field.equals("balance="))
			{
				if(cond.value != Double.valueOf(accBalance))
					feasValues.add(false);
				else
					feasValues.add(true);		

			}

			if(cond.field.equals("date"))
			{
				//default time = 0h
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(8);

				if(String.valueOf(df.format(cond.value)).equals(systemDate[0]) && systemDate[1].equals("00"))
					feasValues.add(true);
				else
					feasValues.add(false);
			}

			if(cond.field.equals("monthly"))
			{
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(8);
				String day = systemDate[0].substring(6);
				if(String.valueOf(df.format(cond.value)).equals(day) && systemDate[1].equals("00"))
					feasValues.add(true);
				else
					feasValues.add(false);
			}

			if(cond.field.equals("yearly"))
			{
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(8);
				String day_month = systemDate[0].substring(4);

				if(String.valueOf(df.format(cond.value)).equals(day_month) && systemDate[1].equals("14"))
					feasValues.add(true);
				else
					feasValues.add(false);
			}


			//if all the conditions are true, make an object and field satisfy is true,
			//if atleast one of the conditions is true, but not all, make an object and field satisfy is false,
			//if all the conditions are false, don't make an object and put it in the table at all

		}

		for(Boolean b:feasValues)
			System.out.println("value: "+ b);
		return feasValues;

	}

	public String getInput_condition() {
		return Input_condition;
	}

	public void setInput_condition(String input_condition) {
		Input_condition = input_condition;
	}

	public String getInput_action() {
		return Input_action;
	}

	public void setInput_action(String input_action) {
		Input_action = input_action;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public LoginDao getDao() {
		return dao;
	}

	public void setDao(LoginDao dao) {
		this.dao = dao;
	}





}
