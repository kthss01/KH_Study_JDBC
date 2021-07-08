package com.kh.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.model.exception.ProductException;
import com.kh.model.vo.Product;
import com.kh.service.ProductService;
import com.kh.view.ProductMenu;

public class ProductController {

	public void exitProgram() {
		new ProductService().exitProgram();
	}
	
	public void selectAll() {
		
		ProductMenu menu = new ProductMenu();
		ArrayList<Product> list;
		
		try {
			list = new ProductService().selectAll();
			
			if (!list.isEmpty()) {
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			menu.displayError("상품 전체 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void insertProduct(Product p) {
		int result;
		
		try {
			result = new ProductService().insertProduct(p);
			if (result > 0) {
				new ProductMenu().displaySuccess("상품 추가 성공");
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 추가 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
	}

	public void updateProduct(Product p) {
		int result;
		
		try {
			result = new ProductService().updateProduct(p);
			if (result > 0) {
				new ProductMenu().displaySuccess("상품 수정 성공");
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 수정 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
	}

	public void deleteProduct(String productId) {
		int result;
		
		try {
			result = new ProductService().deleteProduct(productId);
			if (result > 0) {
				new ProductMenu().displaySuccess("상품 삭제 성공");
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 삭제 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		} // 성공하면 1 반환
	}

	public void selectName(String productName) {
		ProductMenu menu = new ProductMenu();
		List<Product> list;
		
		try {
			list = new ProductService().selectName(productName);
			if (!list.isEmpty()) {
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			menu.displayError("상품 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
		
	}

}
