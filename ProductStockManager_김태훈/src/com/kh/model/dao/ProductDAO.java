package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.model.exception.ProductException;
import com.kh.model.vo.Product;


public class ProductDAO {
	
	private Properties prop = null;
	
	public ProductDAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> selectAll(Connection conn) throws ProductException {
		ArrayList<Product> list = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAll");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			list = new ArrayList<Product>();

			while (rset.next()) {
				Product p = new Product();

				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setpName(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));

				list.add(p);
			}

		} catch (Exception e) {
			throw new ProductException("selectAll 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public List<Product> selectName(Connection conn, String productName) throws ProductException {
		ArrayList<Product> list = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectName");

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Product>();

			while (rset.next()) {
				Product p = new Product();

				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setpName(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));

				list.add(p);
			}

		} catch (Exception e) {
			throw new ProductException("selectName 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	public List<Product> selectId(Connection conn, String productId) throws ProductException {
		ArrayList<Product> list = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectId");

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Product>();

			while (rset.next()) {
				Product p = new Product();

				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setpName(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));

				list.add(p);
			}

		} catch (Exception e) {
			throw new ProductException("selectId 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	public int insertProduct(Connection conn, Product p) throws ProductException {
		int result = 0;
		
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertProduct");

		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ProductException("insertProduct 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateProduct(Connection conn, Product p) throws ProductException {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateProduct");

		try {
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock());
			pstmt.setString(5, p.getProductId());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ProductException("updateProduct 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int deleteProduct(Connection conn, String productId) throws ProductException {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteProduct");

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new ProductException("deleteProduct 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}
}
