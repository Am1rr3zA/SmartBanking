package main.java.com.smartBanking.api;


import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.java.com.smartBanking.Logic.ConvertToString;
import main.java.com.smartBanking.bin.BinCondition;
import main.java.com.smartBanking.bin.BinReport;
import main.java.com.smartBanking.da.ReportDao;

@Path("/report")
public class ReportController {
	
	ReportDao dao = new ReportDao();

	@GET
	@Produces("application/json")
	public Response allReports() throws JSONException, AuthenticationException {
		List<BinReport> reports = dao.getAllReportForUser(1);
		JSONArray resp = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		for (BinReport report:reports){
			jsonObject = new JSONObject();
			
			jsonObject.put("id", report.getId());
			jsonObject.put("rid", report.getRid());
			jsonObject.put("pid", report.getPid());
			jsonObject.put("reportDate", report.getReportdate());
			jsonObject.put("satifiy", report.isSatisfy());
			String trig = report.getTriger();
			JSONArray trigArray = new JSONArray();
			JSONObject trigObject = new JSONObject();
			if (trig != null){
				List<BinCondition> trigConds = ConvertToString.stringToBinConditions(trig);
				for (BinCondition trigCond:trigConds){
					trigObject.put("type".trim(), trigCond.getType().trim());
					trigObject.put("cond".trim(), trigCond.getCond().trim());
					trigArray.put(trigObject);
				}
//				trig = trigArray.toString();
			}
			jsonObject.put("triggered", trigArray);
			String rej = report.getReject();
			JSONArray rejArray = new JSONArray();
			JSONObject rejObject = new JSONObject();
			if (rej != null){
				List<BinCondition> rejConds = ConvertToString.stringToBinConditions(rej);
				for (BinCondition rejCond:rejConds){
					rejObject.put("type".trim(), rejCond.getType().trim());
					rejObject.put("cond".trim(), rejCond.getCond().trim());
//					rejArray.put(rejObject);
				}
				rej = rejArray.toString();
			}
			jsonObject.put("rejected", rejArray);
			
			resp.put(jsonObject);
		}

	    String result = resp.toString();
		
		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
			    .entity(result).build();		
		
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response specificReport() throws JSONException, AuthenticationException {
		List<BinReport> reports = dao.getAllReportForRule(1);
		JSONArray resp = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		for (BinReport report:reports){
			jsonObject = new JSONObject();
			
			jsonObject.put("id", report.getId());
			jsonObject.put("rid", report.getRid());
			jsonObject.put("pid", report.getPid());
			jsonObject.put("reportDate", report.getReportdate());
			jsonObject.put("satifiy", report.isSatisfy());
			String trig = report.getTriger();
			JSONArray trigArray = new JSONArray();
			JSONObject trigObject = new JSONObject();
			if (trig != null){
				List<BinCondition> trigConds = ConvertToString.stringToBinConditions(trig);
				for (BinCondition trigCond:trigConds){
					trigObject.put("type".trim(), trigCond.getType().trim());
					trigObject.put("cond".trim(), trigCond.getCond().trim());
					trigArray.put(trigObject);
				}
//				trig = trigArray.toString();
			}
			jsonObject.put("triggered", trigArray);
			String rej = report.getReject();
			JSONArray rejArray = new JSONArray();
			JSONObject rejObject = new JSONObject();
			if (rej != null){
				List<BinCondition> rejConds = ConvertToString.stringToBinConditions(rej);
				for (BinCondition rejCond:rejConds){
					rejObject.put("type".trim(), rejCond.getType().trim());
					rejObject.put("cond".trim(), rejCond.getCond().trim());
					rejArray.put(rejObject);
				}
//				rej = rejArray.toString();
			}
			jsonObject.put("rejected", rejArray);
			
			resp.put(jsonObject);
		}

	    String result = resp.toString();
		
		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
			    .entity(result).build();		
		
	}
}
