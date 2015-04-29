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
		System.out.println(zipcode);
		String getUserQuery ="SELECT shopname,address FROM location where zipcode=?";
		preparedstmt = conn.prepareStatement(getUserQuery);
		preparedstmt.setString(1,zipcode);
		rs = preparedstmt.executeQuery();
		JSONArray list;
		JSONObject obj = new JSONObject();
		String i="1";
		while(rs.next())
		{
			list =new JSONArray();
			list.put(rs.getString("shopname"));
			list.put(rs.getString("address"));	
			obj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		/*JSONArray list =new JSONArray();
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
		obj.put("2", list1);*/
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
		JSONArray list;
		JSONObject couponObj = new JSONObject();
		String i="1";
		couponObj.put("shopname",shopName);
		while(rs.next())
		{
			list =new JSONArray();
			list.put(rs.getString("coupon_title"));
			list.put(rs.getString("coupon_desc"));
			list.put(rs.getString("image_url"));
			list.put(rs.getString("value"));
			couponObj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		return couponObj;
	}
}
