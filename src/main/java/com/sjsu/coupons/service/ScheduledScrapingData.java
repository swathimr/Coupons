package com.sjsu.coupons.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ScheduledScrapingData {
	
	
	public final static String USERNAME="root";
	public final static String PASSWORD="cmpe280_team21";
	public final static String DB_STRING = "jdbc:mysql://cmpe280.cotpkp31md0p.us-west-1.rds.amazonaws.com:3306/cmpe280";
	Connection conn;
	Statement stmt = null;
	PreparedStatement preparedstmt;
	ResultSet rs;
	
	 private  String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public  JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }

		  
		  
		  public  void addWalmartCoupons() throws IOException, JSONException, SQLException
		  {
			  JSONObject json = readJsonFromUrl("https://api.import.io/store/data/bd49db85-2efc-44f7-b545-7c0c443d3a86/_query?input/webpage/url=http%3A%2F%2Fcoupons.walmart.com%2F&_user=1f63de9e-cda2-4b72-ad84-c3d6b516838e&_apikey=1f63de9e-cda2-4b72-ad84-c3d6b516838e%3Av90vaGiX7Gm7pBirfbHEo9anRfUBkZ1LSrtZ3uL1E6yW5XGE1C5WZqCOJ7P9Ar9j%2BcvEy%2FRUZxRphhGXkipLJQ%3D%3D");
			    System.out.println(json.toString());
			    JSONArray json1 = (JSONArray)json.get("results");
			    for (int i = 0; i < json1.length(); i++) {
			    	  JSONObject js = json1.getJSONObject(i);
			    	  insertIntoDB("walmart", js.get("offerproduct_value").toString() , js.get("offerdetails_description").toString() ,js.get("pod_image/_source").toString(),js.get("offer_value").toString());
			    	}
		  }
		  



			private void insertIntoDB(String shopname, String couponname, String coupondesc,
					String coupon_image_url, String coupon_value) throws SQLException {
				
				conn = SqlFactory.getConnection();
				//Statement stmt=conn.createStatement();
				String query = " insert into coupons (shopname,coupon_title,coupon_desc,image_url,value)"
		    	        + " values ( ?, ?, ?, ?, ?)";
				
				 PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString (1,shopname);
			      preparedStmt.setString (2,couponname);
			      preparedStmt.setString(3,coupondesc);
			      preparedStmt.setString(4,coupon_image_url);
			      preparedStmt.setString(5,coupon_value);
			      preparedStmt.execute();
			          
				
				
				
				
				
				//System.out.println(shopname + "\t" + couponname + "\t" + coupondesc + "\t" + coupon_image_url + "\t" + coupon_value + "/n");
			}
			
			private void deleteFromDB() throws SQLException
			{
				String query = "Delete from cmpe280.coupons";
				 PreparedStatement preparedStmt = conn.prepareStatement(query);
				 preparedStmt.execute();
				 
			}

		public  void addTargetCoupons() throws IOException, JSONException, SQLException
		  {
			  JSONObject json = readJsonFromUrl("https://api.import.io/store/data/ed275137-963b-4843-9d97-2b09747f79c2/_query?input/webpage/url=http%3A%2F%2Fcoupons.target.com%2F&_user=1f63de9e-cda2-4b72-ad84-c3d6b516838e&_apikey=1f63de9e-cda2-4b72-ad84-c3d6b516838e%3Av90vaGiX7Gm7pBirfbHEo9anRfUBkZ1LSrtZ3uL1E6yW5XGE1C5WZqCOJ7P9Ar9j%2BcvEy%2FRUZxRphhGXkipLJQ%3D%3D");
			    System.out.println(json.toString());
			    JSONArray json1 = (JSONArray)json.get("results");
			    for (int i = 0; i < json1.length(); i++) {
			    	  JSONObject js = json1.getJSONObject(i);
			    	  
			    	  insertIntoDB("Target", js.get("desc_link/_text").toString() , js.get("desc_description").toString() ,js.get("coupimage_image").toString(),js.get("hide_value_prices/_source").toString());
			    	}
		  }
		  
		public  void addMacysCoupons() throws IOException, JSONException, SQLException
		{
			  JSONObject json = readJsonFromUrl("https://api.import.io/store/data/c8e4cc24-4ccb-40e2-8d39-d4f6fbb26361/_query?input/webpage/url=http%3A%2F%2Fwww.retailmenot.com%2Fview%2Fmacys.com&_user=1f63de9e-cda2-4b72-ad84-c3d6b516838e&_apikey=1f63de9e-cda2-4b72-ad84-c3d6b516838e%3Av90vaGiX7Gm7pBirfbHEo9anRfUBkZ1LSrtZ3uL1E6yW5XGE1C5WZqCOJ7P9Ar9j%2BcvEy%2FRUZxRphhGXkipLJQ%3D%3D");
			    System.out.println(json.toString());
			    JSONArray json1 = (JSONArray)json.get("results");
			    for (int i = 0; i < json1.length(); i++) {
			    	  JSONObject js = json1.getJSONObject(i);
			    	  
			    	  insertIntoDB("Macys", js.get("title_link/_text").toString() , js.get("truncated_description").toString() ,"http://samicone.com/wp-content/uploads/2014/10/macys.jpg",js.has("anchorimage_values")?js.get("anchorimage_values").toString():null);
			    	}
		}


		  public  void addKohlsCoupons() throws IOException, JSONException, SQLException
		  {
			  JSONObject json = readJsonFromUrl("https://api.import.io/store/data/b68bcd56-eb18-4ab7-8e76-cef6aa2800ce/_query?input/webpage/url=http%3A%2F%2Fwww.kohls.com%2Fsale-event%2Fcoupons-deals.jsp&_user=1f63de9e-cda2-4b72-ad84-c3d6b516838e&_apikey=1f63de9e-cda2-4b72-ad84-c3d6b516838e%3Av90vaGiX7Gm7pBirfbHEo9anRfUBkZ1LSrtZ3uL1E6yW5XGE1C5WZqCOJ7P9Ar9j%2BcvEy%2FRUZxRphhGXkipLJQ%3D%3D");
			    
			    JSONArray json1 = (JSONArray)json.get("results");
			    for (int i = 0; i < json1.length(); i++) {
			    	  JSONObject js = json1.getJSONObject(i);
			    	  
			    	  insertIntoDB("Kohls", js.get("headline_values").toString() , js.get("dealcontent_content").toString() ,js.get("dealimage_image").toString(),js.has("dealcontent_content_prices/_source")?js.get("dealcontent_content_prices/_source").toString():null);
			    	}
		  }
		  
		  public  void addStaplesCoupons() throws IOException, JSONException, SQLException
		  {
			  JSONObject json = readJsonFromUrl("https://api.import.io/store/data/9c0a7636-9d0a-4a9f-b129-43e36fc77dae/_query?input/webpage/url=http%3A%2F%2Fwww.staples.com%2Fcoupons&_user=1f63de9e-cda2-4b72-ad84-c3d6b516838e&_apikey=1f63de9e-cda2-4b72-ad84-c3d6b516838e%3Av90vaGiX7Gm7pBirfbHEo9anRfUBkZ1LSrtZ3uL1E6yW5XGE1C5WZqCOJ7P9Ar9j%2BcvEy%2FRUZxRphhGXkipLJQ%3D%3D");
			    System.out.println(json.toString());
			    JSONArray json1 = (JSONArray)json.get("results");
			    for (int i = 0; i < json1.length(); i++) {
			    	  JSONObject js = json1.getJSONObject(i);
			    	  
			    	  insertIntoDB("Staples", js.get("producttitle_value").toString() , js.get("item_value").toString() ,js.has("noprint_image")?js.get("noprint_image").toString():null,js.has("item_value_prices")?js.get("item_value_prices").toString():null);
			    	}
		  }
		  


}
