package com.sjsu.coupons.service;

import java.sql.*;

import com.mysql.jdbc.Connection;
import com.sjsu.coupons.domain.User;

public class UserHandler {

	Connection conn;
	Statement stmt = null;
	PreparedStatement preparedstmt;
	ResultSet rs;
	
	public void signUpUser(User user) throws SQLException
	{
		conn = SqlFactory.getConnection();
		stmt=conn.createStatement();
		String query = " insert into users (name,email,password)"
    	        + " values ( ?, ?, ?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1,user.getName());
	      preparedStmt.setString (2,user.getEmail());
	      preparedStmt.setString(3,user.getPassword());
	      preparedStmt.execute();
	      conn.close();      
	}
	
	public boolean checkIfUserExists(String email) throws SQLException
	{
		String emailVal=null;
		boolean returnValue = false;
		conn = SqlFactory.getConnection();
		String getUserQuery ="select email from users where email=?";
		preparedstmt = conn.prepareStatement(getUserQuery);
		preparedstmt.setString(1,email);
		rs = preparedstmt.executeQuery();
	//	if(rs.)
		while(rs.next())
		{
			emailVal= rs.getString("email");
			System.out.println(emailVal);
		}
		SqlFactory.cleanup(preparedstmt, rs, conn);
		
		if(emailVal == null)
		{
			returnValue = false;
		}
		if(emailVal!=null){
			
			returnValue = true;
		}
		return returnValue;
	}
	
}
