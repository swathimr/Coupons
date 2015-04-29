package com.sjsu.coupons.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class HomeController 
{
	public static void main(String[] args) throws Exception {
        SpringApplication.run(RestConfig.class, args);
    }
}
