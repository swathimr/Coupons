
package com.sjsu.coupons.controller;


import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sjsu.coupons.service.GraphHandler;





@Controller
public class GraphController {

	
	GraphHandler graphHandler = new GraphHandler();
	
	@RequestMapping(value = ("/ShowGraph1"))
	public String showGraph1(Model model)
	{
		
		return "graph1";
	}
	
	@RequestMapping(value = ("/getGraph1"),method=RequestMethod.GET)
	@ResponseBody
	public 	String getGraph1(Model model) throws SQLException
	{
		JSONObject json= graphHandler.getGraph1Data();
		return json.toString();
	}
	
	
	@RequestMapping(value = ("/ShowGraph2"))
	public String showGraph2(Model model)
	{
		
		return "graph2";
	}
	
	@RequestMapping(value = ("/getGraph2"),method=RequestMethod.GET)
	@ResponseBody
	public 	String getGraph2(Model model) throws SQLException
	{
		JSONObject json= graphHandler.getGraph2Data();
		return json.toString();
	}
	
	
	
	@RequestMapping(value = ("/ShowGraph3"))
	public String showGraph3(Model model)
	{
		
		return "graph3";
	}
	
	
	@RequestMapping(value = ("/getGraph3"),method=RequestMethod.GET)
	@ResponseBody
	public 	String getGraph3(Model model) throws SQLException
	{
		JSONObject json= graphHandler.getGraph3Data();
		return json.toString();
	}
	
	
	
	@RequestMapping(value = ("/ShowGraph4"))
	public String getLocation(Model model)
	{
		
		return "graph4";
	}
	
}