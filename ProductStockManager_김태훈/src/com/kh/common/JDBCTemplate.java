package com.kh.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				// 외부에서 정보를 읽어와서 저장할 Properties 객체 선언 및 생성
				Properties prop = new Properties();
				
				prop.load(new FileReader("resources/driver.properties"));
				
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(
						prop.getProperty("url"), 
						prop.getProperty("user"), 
						prop.getProperty("password"));
				
				conn.setAutoCommit(false);
				
				System.out.println("JDBC 연결 성공");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) { 
		// PreparedStatement는 Statement 하위 클래스라 따로 안해줘도 됨
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
