package com.kh.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.model.dao.ProductDAO;
import com.kh.model.exception.ProductException;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductService {

	public void exitProgram() {
		close(getConnection());
	}

	public ArrayList<Product> selectAll() throws ProductException {
		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDAO().selectAll(conn);

		return list;
	}

	public List<Product> selectName(String productName) throws ProductException {
		Connection conn = getConnection();

		List<Product> list = new ProductDAO().selectName(conn, productName);

		return list;
	}

	public int insertProduct(Product p) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductDAO().insertProduct(conn, p); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int updateProduct(Product p) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductDAO().updateProduct(conn, p);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int deleteProduct(String productId) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductDAO().deleteProduct(conn, productId); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}
}
