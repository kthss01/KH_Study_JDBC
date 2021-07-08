package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.model.exception.ProductException;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductIODAO {

	private Properties prop = null;

	public ProductIODAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProductIO> selectAllIO(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllIO");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			list = new ArrayList<ProductIO>();

			while (rset.next()) {
				int ioNum;
				String productId;
				String pName; // 조인해서 받아올거
				Date ioDate;
				int amount;
				String status;

				ioNum = rset.getInt(1);
				productId = rset.getString(2);
				pName = rset.getString(3);
				ioDate = rset.getDate(4);
				amount = rset.getInt(5);
				status = rset.getString(6);

				list.add(new ProductIO(ioNum, productId, pName, ioDate, amount, status));
			}

		} catch (Exception e) {
			throw new ProductException("selectAllIO 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public ArrayList<ProductIO> selectIn(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectIn");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			list = new ArrayList<ProductIO>();

			while (rset.next()) {
				int ioNum;
				String productId;
				String pName; // 조인해서 받아올거
				Date ioDate;
				int amount;
				String status;

				ioNum = rset.getInt(1);
				productId = rset.getString(2);
				pName = rset.getString(3);
				ioDate = rset.getDate(4);
				amount = rset.getInt(5);
				status = rset.getString(6);

				list.add(new ProductIO(ioNum, productId, pName, ioDate, amount, status));
			}

		} catch (Exception e) {
			throw new ProductException("selectIn 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public ArrayList<ProductIO> selectOut(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectOut");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			list = new ArrayList<ProductIO>();

			while (rset.next()) {
				int ioNum;
				String productId;
				String pName; // 조인해서 받아올거
				Date ioDate;
				int amount;
				String status;

				ioNum = rset.getInt(1);
				productId = rset.getString(2);
				pName = rset.getString(3);
				ioDate = rset.getDate(4);
				amount = rset.getInt(5);
				status = rset.getString(6);

				list.add(new ProductIO(ioNum, productId, pName, ioDate, amount, status));
			}

		} catch (Exception e) {
			throw new ProductException("selectOut 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int insertProductIn(Connection conn, ProductIO p) throws ProductException {
		int result = 0;
		
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertProductIn");

		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductId());
			pstmt.setInt(2, p.getAmount());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ProductException("insertProductIn 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertProductOut(Connection conn, ProductIO p) throws ProductException {
		int result = 0;
		
		List<Product> pList = new ProductDAO().selectId(conn, p.getProductId());
		if (pList.get(0).getStock() < p.getAmount()) {
			throw new ProductException("출고하고자 하는 제품의 재고수량이 부족합니다.");
		}
		
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertProductOut");

		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductId());
			pstmt.setInt(2, p.getAmount());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ProductException("insertProductOut 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}

}
