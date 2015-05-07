package com.sjsu.coupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ("/UserHomePage"))
public class CouponsHomeCtrl {

	@RequestMapping
	public String getCouponHomePage()
	{
		return "UserHomePage";
	}
	
}
