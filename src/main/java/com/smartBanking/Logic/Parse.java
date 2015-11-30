package main.java.com.smartBanking.Logic;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;


import com.bahmanm.persianutils.DateConverter;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.json.JSONJAXBContext.JSONNotation;

import main.java.com.smartBanking.Exceptions.notFound;
import main.java.com.smartBanking.api.LoginController;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinLogin;
import main.java.com.smartBanking.bin.BinRule;
import main.java.com.smartBanking.da.LoginDao;
import main.java.com.smartBanking.services.BankAPI;
import main.java.com.smartBanking.services.timeConversion;

public class Parse {

	//String Input_condition;
	//String Input_action;
	String token;
	String accountNum;
	LoginDao dao = new LoginDao();




	public Parse(BinRule input, BinLogin user) throws notFound
	{
		if(user.getAccess_token()!= null)
			token = user.getAccess_token();
		else
			throw new notFound("Not found access-token");

		if(user.getAccounts()!= null)
			accountNum = user.getAccounts().get(0);
		else
			throw new notFound("Not found account number");
	}




	public List<Boolean> feasibility(List<BinCondition> condition) throws ParseException
	{

		timeConversion tc = new timeConversion();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd,HH");
		//get current date time with Date()
		Date date = new Date();
		String[] systemDate = dateFormat.format(date).toString().split(",");



		//System.out.println("system date is : "+systemDate[0]);
		List<Boolean> feasValues = new ArrayList<>();

		for(BinCondition bincondition:condition)
		{

			System.out.println("condition code "+bincondition.getType());
			if(bincondition.getType().equals("1") || bincondition.getType().equals("2") || bincondition.getType().equals("3"))
			{
				ClientResponse response = BankAPI.getBalance(token, accountNum);
				JSONObject jObject  = new JSONObject(response.getEntity(String.class));

				String balance = jObject.get("additionalData").toString();

				if(bincondition.getType().equals("1"))
				{
					if(Double.valueOf(bincondition.getCond()) >= Double.valueOf(balance))
						feasValues.add(false);
					else
						feasValues.add(true);
				}

				if(bincondition.getType().equals("2"))
				{
					if(Double.valueOf(bincondition.getCond()) <= Double.valueOf(balance))
						feasValues.add(false);
					else
						feasValues.add(true);
				}

				if(bincondition.getType().equals("3"))
				{
					if(Double.valueOf(bincondition.getCond()) != Double.valueOf(balance))
						feasValues.add(false);
					else
						feasValues.add(true);
				}
			}


			if(bincondition.getType().equals("4"))
			{
				if(bincondition.getCond().indexOf("-") ==-1)
				{
					//default time = 0h
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					String bintime = tc.jalaliTogregorian(bincondition);
					Date ruleDate = df.parse(bintime);
					Date sysDate = df.parse(systemDate[0]);
					if(ruleDate.equals(sysDate) && systemDate[1].equals("00"))
						feasValues.add(true);
					else
						feasValues.add(false);

				}
				else
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");	
					String[] bintime = tc.jalaliTogregorian(bincondition, "-");
					Date ruleDateStart = df.parse(bintime[0]);
					Date ruleDateEnd = df.parse(bintime[1]);
					Date sysDate = df.parse(systemDate[0]);				
					if(sysDate.after(ruleDateStart) && sysDate.before(ruleDateEnd) && systemDate[1].equals("00"))
						feasValues.add(true);
					else
						feasValues.add(false);
				}
			}

			if(bincondition.getType().equals("5"))
			{

				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(8);
				String day = tc.GregorianToJalali(systemDate[0]).substring(6);
				if(String.valueOf(df.format(bincondition.getCond())).equals(day) && systemDate[1].equals("00"))
					feasValues.add(true);
				else
					feasValues.add(false);
			}

			if(bincondition.getType().equals("6"))
			{

			}

			if(bincondition.getType().equals("7"))
			{
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(8);

				String day_month =tc.GregorianToJalali(systemDate[0]).substring(4);

				if(String.valueOf(df.format(bincondition.getCond())).equals(day_month) && systemDate[1].equals("00"))
					feasValues.add(true);
				else
					feasValues.add(false);
			}

			if(bincondition.getType().equals("8"))
			{

				String fromDate = tc.GregorianToJalali(systemDate[0]);
				fromDate = fromDate.substring(0, 6)+"01";
				String toDate = tc.GregorianToJalali(systemDate[0]);
				ClientResponse response = BankAPI.getAccountFullStatement(token, accountNum,fromDate ,toDate);
				JSONObject jObject  = new JSONObject(response.getEntity(String.class));
				JSONArray additionalData = jObject.getJSONArray("additionalData"); 
				System.out.println(additionalData.length());
				if(Integer.valueOf(bincondition.getCond()) < additionalData.length())
					feasValues.add(true);
				else
					feasValues.add(false);
			}

			if(bincondition.getType().equals("9") || bincondition.getType().equals("10") || bincondition.getType().equals("11") || bincondition.getType().equals("12"))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				Calender cal = new Calender(tc.GregorianToJalali(systemDate[0]));
				ClientResponse response = BankAPI.getAccountFullStatement(token, accountNum,cal.Yesterday(cal) ,tc.GregorianToJalali(systemDate[0]));
				//ClientResponse response = BankAPI.getAccountFullStatement(token, accountNum,"13940901" ,"13940909");
				String responseString = response.getEntity(String.class);
				
					JSONObject jObject  = new JSONObject(responseString);
					JSONArray additionalData = jObject.getJSONArray("additionalData");
					if(additionalData.length()>0)
					{
						System.out.println(additionalData);

					if(bincondition.getType().equals("9"))
					{
						System.out.println("in 9: ");
						//System.out.println("description: "+additionalData.getJSONObject(0).getString("description"));
						boolean flag = false;
						for(int i=0; i<additionalData.length(); i++)
						{
							//System.out.println("additional Data "+ additionalData.getJSONObject(i).get("description"));
							if(additionalData.getJSONObject(i).get("description").equals("447"))
							{
								System.out.println(additionalData.getJSONObject(i).get("amount").toString()+"------"+Integer.valueOf(bincondition.getCond()));
								if(Integer.valueOf(additionalData.getJSONObject(i).get("amount").toString()) > Integer.valueOf(bincondition.getCond()))
									flag = true;
							}
						}

						if(flag)
							feasValues.add(true);
						else
							feasValues.add(false);

					}

					if(bincondition.getType().equals("10"))
					{
						for(int i=0; i<additionalData.length(); i++)
						{
							if(additionalData.getJSONObject(i).get("description").equals("447"))
							{
								if(Integer.valueOf(additionalData.getJSONObject(i).get("amount").toString()) < Integer.valueOf(bincondition.getCond()))
									feasValues.add(true);
								else
									feasValues.add(false);
							}
						}
					}

					if(bincondition.getType().equals("11"))
					{
						for(int i=0; i<additionalData.length(); i++)
						{
							if(additionalData.getJSONObject(i).get("description").equals("446"))
							{
								if(Integer.valueOf(additionalData.getJSONObject(i).get("amount").toString()) > Integer.valueOf(bincondition.getCond()))
									feasValues.add(true);
								else
									feasValues.add(false);
							}
						}
					}

					if(bincondition.getType().equals("12"))
					{
						for(int i=0; i<additionalData.length(); i++)
						{
							if(additionalData.getJSONObject(i).get("description").equals("446"))
							{
								if(Integer.valueOf(additionalData.getJSONObject(i).get("amount").toString()) < Integer.valueOf(bincondition.getCond()))
									feasValues.add(true);
								else
									feasValues.add(false);
							}
						}
					}
				}
				else
				{
					feasValues.add(false);
				}
			}
			if(bincondition.getCond().equals("13"))
			{

			}

			if(bincondition.getCond().equals("14"))
			{

			}

		}





		//for(Boolean b:feasValues)
		//	System.out.println("value: "+ b);
		return feasValues;

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
