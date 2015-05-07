
package com.sjsu.coupons.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class GraphController {

	
	
	@RequestMapping(value = ("/ShowGraph1"))
	public String showGraph1(Model model)
	{
		
		return "graph1";
	}
	
	@RequestMapping(value = ("/ShowGraph2"))
	public String showGraph2(Model model)
	{
		
		return "graph2";
	}
	
	@RequestMapping(value = ("/ShowGraph3"))
	public String showGraph3(Model model)
	{
		
		return "graph3";
	}
	
	@RequestMapping(value = ("/ShowGraph4"))
	public String getLocation(Model model)
	{
		
		return "graph4";
	}
	
}