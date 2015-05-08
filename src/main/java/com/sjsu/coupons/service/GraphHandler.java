package com.sjsu.coupons.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;

public class GraphHandler {
	Connection conn;
	Statement stmt = null;
	PreparedStatement preparedstmt;
	ResultSet rs;
	
	public JSONObject getGraph1Data() throws SQLException
	{
		
		conn = SqlFactory.getConnection();
		String getUserQuery ="SELECT * FROM graph1";
		
		preparedstmt = conn.prepareStatement(getUserQuery);
		
		rs = preparedstmt.executeQuery();
		JSONObject list;
		JSONObject obj = new JSONObject();
		String i="1";
		while(rs.next())
		{
			list =new JSONObject();
			list.put("shopname",rs.getString("shopname"));
			list.put("value1",rs.getString("value1"));
			list.put("value2",rs.getString("value2"));
			list.put("value3",rs.getString("value3"));
			obj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		System.out.println("created json object is::"+obj);
		return obj;
	}
	
	
	public JSONObject getGraph2Data() throws SQLException
	{
		
		conn = SqlFactory.getConnection();
		String getUserQuery ="SELECT * FROM graph2";
		
		preparedstmt = conn.prepareStatement(getUserQuery);
		
		rs = preparedstmt.executeQuery();
		JSONObject list;
		JSONObject obj = new JSONObject();
		String i="1";
		while(rs.next())
		{
			list =new JSONObject();
			list.put("v1",rs.getString("v1"));
			list.put("v2",rs.getString("v2"));
			list.put("v3",rs.getString("v3"));
			obj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		System.out.println("created json object is::"+obj);
		return obj;
	}

	public JSONObject getGraph3Data() throws SQLException
	{
		
		conn = SqlFactory.getConnection();
		String getUserQuery ="SELECT * FROM graph3";
		
		preparedstmt = conn.prepareStatement(getUserQuery);
		
		rs = preparedstmt.executeQuery();
		JSONObject list;
		JSONObject obj = new JSONObject();
		String i="1";
		while(rs.next())
		{
			list =new JSONObject();
			list.put("shopname",rs.getString("shopname"));
			list.put("v1",rs.getString("v1"));
			list.put("v2",rs.getString("v2"));
			list.put("v3",rs.getString("v3"));
			list.put("v4",rs.getString("v4"));
			list.put("v5",rs.getString("v5"));
			list.put("v6",rs.getString("v6"));
			list.put("v7",rs.getString("v7"));
			
			obj.put(i,list);
			i = String.valueOf(Integer.parseInt(i) + 1);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		System.out.println("created json object is::"+obj);
		return obj;
	}
	
	
}
