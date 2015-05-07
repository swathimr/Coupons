package com.sjsu.coupons.controller;

import java.sql.SQLException;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.coupons.domain.*;
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
	
	@RequestMapping(value = ("/getLocationList"),produces="application/json",method=RequestMethod.POST)
	@ResponseBody
	public String getCouponLocationList(@RequestBody String address,Model model) throws SQLException
	{
		System.out.println("address got from js:::"+address);
		locationHndlr = new LocationHandler();
		String[] newAddress = address.split("=");
		newAddress[1].toString();
		JSONObject json= locationHndlr.getLocationCouponList(newAddress[1].toString());
		model.addAttribute("locationList", json);
		return json.toString();
	}
	
	@RequestMapping(value = ("/getCouponList"),produces="application/json",method=RequestMethod.POST)
	@ResponseBody
	public String getCouponList(@RequestBody String shopname,Model model) throws SQLException
	{
		System.out.println("ShopName got from js:::"+shopname);
		locationHndlr = new LocationHandler();
		String[] newShopName = shopname.split("=");
		newShopName[1].toString();
		JSONObject json= locationHndlr.getCouponList(newShopName[1].toString());
		//JSON
		model.addAttribute("CouponList", json);
		return json.toString();
	}
	
}
