package com.sjsu.coupons.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjsu.coupons.domain.User;


@Controller
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RestConfig {
	
	@RequestMapping("/coupons")
    public String home(Model model) {
		model.addAttribute("user", new User());
        return "/Coupons";
    }	
}
