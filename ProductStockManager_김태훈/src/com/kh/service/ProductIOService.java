package com.kh.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductIODAO;
import com.kh.model.exception.ProductException;
import com.kh.model.vo.ProductIO;

public class ProductIOService {

	public ArrayList<ProductIO> selectAllIO() throws ProductException {
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductIODAO().selectAllIO(conn);

		return list;
	}

	public ArrayList<ProductIO> selectIn() throws ProductException {
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductIODAO().selectIn(conn);

		return list;
	}

	public ArrayList<ProductIO> selectOut() throws ProductException {
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductIODAO().selectOut(conn);

		return list;
	}

	public int insertProductIn(ProductIO p) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductIODAO().insertProductIn(conn, p); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int insertProductOut(ProductIO p) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductIODAO().insertProductOut(conn, p); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

}
