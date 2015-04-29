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
public class ShopNameController {
	LocationHandler locationHndlr;
	
	@RequestMapping(value = ("/ByShopName"),method=RequestMethod.POST)
	public String getShopName(Model model,@ModelAttribute User user)
	{
		model.addAttribute("user",user);
		return "ByShopName";
	}
	
	@RequestMapping(value = ("/getShopCouponList"),produces="application/json",method=RequestMethod.POST)
	@ResponseBody
	public String getShopCouponList(@RequestBody String shopname,Model model) throws SQLException
	{
		locationHndlr = new LocationHandler();
		String[] newShopName = shopname.split("=");
		newShopName[1].toString();
		JSONObject json= locationHndlr.getCouponList(newShopName[1].toString());
		//model.addAttribute("CouponList", json.ge);
		return json.toString();
	}
}
