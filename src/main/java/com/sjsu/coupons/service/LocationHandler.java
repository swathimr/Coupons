package com.sjsu.coupons.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;

public class LocationHandler {
	
	Connection conn;
	Statement stmt = null;
	PreparedStatement preparedstmt;
	ResultSet rs;
	
	public JSONObject getLocationCouponList(String zipcode) throws SQLException
	{
		
		conn = SqlFactory.getConnection();
	/*JSONObject obj = new JSONObject();
	JSONArray list = new JSONArray();
	list.put();*/
	//obj.p
		JSONArray list =new JSONArray();
		list.put("1");
		list.put("target");
		list.put("5$");
		list.put("http://files.cncdn.co/dam/17/pah/thumbnail/images/DOStandard100/698918.jpg");
		list.put("5/28/2015");
		list.put("Common Pacific Owners Association, 43950 Pacific Commons Blvd, Fremont, CA 94538");
		JSONArray list1 =new JSONArray();
		list1.put("1");
		list1.put("target");
		list1.put("5$");
		list1.put("http://files.cncdn.co/dam/17/pah/thumbnail/images/DOStandard100/698918.jpg");
		list1.put("5/28/2015");
		list1.put("Fremont Hub Shopping Center, 39201 Fremont Blvd, Fremont, CA 94538");
		JSONObject obj = new JSONObject();
		obj.put("1",list);
		obj.put("2", list1);
		System.out.println("created json object is::"+obj);
		return obj;
	}
	
	public JSONObject getCouponList(String shopName) throws SQLException
	{
		conn = SqlFactory.getConnection();
		String getUserQuery ="select * from coupons where shopname=?";
		preparedstmt = conn.prepareStatement(getUserQuery);
		preparedstmt.setString(1,shopName);
		rs = preparedstmt.executeQuery();
		JSONObject list;
		JSONObject couponObj = new JSONObject();
		String i="1";
		String url = getUrl(shopName);
		while(rs.next())
		{
			list =new JSONObject();
			list.put("coupon_title",rs.getString("coupon_title"));
			list.put("coupon_desc",rs.getString("coupon_desc"));
			list.put("image_url",rs.getString("image_url"));
			list.put("value",rs.getString("value"));
			list.put("url",url);
			couponObj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		return couponObj;
	}

	private String getUrl(String shopName) {
		if(shopName.toLowerCase().equals("target"))
			return "http://coupons.target.com/";
		if(shopName.toLowerCase().equals("staples"))
			return "http://www.staples.com/coupons";
		if(shopName.toLowerCase().equals("walmart"))
			return "http://coupons.walmart.com/";
		if(shopName.toLowerCase().equals("kohls"))
			return "http://www.kohls.com/sale-event/coupons-deals.jsp";
		if(shopName.toLowerCase().equals("macys"))
			return "http://www.retailmenot.com/view/macys.com";
		return "";
		
	}

}
