package com.sjsu.coupons.controller;

import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.coupons.domain.User;
import com.sjsu.coupons.service.LocationHandler;

@Controller
public class LocationController {

	LocationHandler locationHndlr;
	
	@RequestMapping(value = ("/ByLocation"),method=RequestMethod.POST)
	public String getLocation(Model model,@ModelAttribute User user)
	{
		model.addAttribute("user",user);
		return "ByLocation";
	}
	
	@RequestMapping(value = ("/getCouponList"),produces="application/json",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCouponLocationList(@RequestBody String address,Model model) throws SQLException
	{
		System.out.println("address got from js:::"+address);
		locationHndlr = new LocationHandler();
		JSONObject json= locationHndlr.getLocationCouponList(address);
		model.addAttribute("locationList", json);
		return json;
	}
}
