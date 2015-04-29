package com.sjsu.coupons.controller;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sjsu.coupons.service.*;


@Component
public class ScheduledTaskController {
	
	ScheduledScrapingData s = new ScheduledScrapingData();
	
	@Scheduled(fixedRate = 5000)
    public void scrapeData() throws IOException, JSONException{
        s.addKohlsCoupons();
        s.addMacysCoupons();
        s.addStaplesCoupons();
        s.addTargetCoupons();
        s.addWalmartCoupons();
        
    }


}
