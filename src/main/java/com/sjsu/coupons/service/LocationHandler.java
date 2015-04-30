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
		String url = getUrl(shopName);
		couponObj.put("shopname",shopName);
		while(rs.next())
		{
			list =new JSONArray();
			list.put(rs.getString("coupon_title"));
			list.put(rs.getString("coupon_desc"));
			list.put(rs.getString("image_url"));
			list.put(rs.getString("value"));
			list.put(url);
			couponObj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		return couponObj;
	}
	
	
	public JSONObject getCouponByShopList(String shopName) throws SQLException
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
