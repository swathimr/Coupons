package com.sjsu.coupons.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class SqlFactory {

	public final static String USERNAME="root";
	public final static String PASSWORD="cmpe280_team21";
	public final static String DB_STRING = "jdbc:mysql://cmpe280.cotpkp31md0p.us-west-1.rds.amazonaws.com:3306/cmpe280";
	
	/**
	 * Connects to AWS RDS MySQL Instance
	 * @return Connection
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			 //loading JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load MySQL JDBC driver. " +  e.getMessage());
			e.printStackTrace();
		}
		
		try {
			//connecting to the MySQL DB
			conn = (Connection) DriverManager.getConnection(DB_STRING, USERNAME, PASSWORD); 
			System.out.println("Connected to the database.");
		} catch (SQLException e) {
			System.err.println("Error in connecting to the database. " +  e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Closes JDBC resources
	 */
	public static void cleanup (PreparedStatement ps, ResultSet rs, Connection con){
		try {
			if(ps != null){
				ps.close();
			}
			
			if(rs != null){
				rs.close();
			}
			
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
