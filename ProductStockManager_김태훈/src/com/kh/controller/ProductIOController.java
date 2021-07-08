package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.exception.ProductException;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;
import com.kh.service.ProductIOService;
import com.kh.service.ProductService;
import com.kh.view.ProductMenu;

public class ProductIOController {

	public void selectAllIO() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		
		try {
			list = new ProductIOService().selectAllIO();
			
			if (!list.isEmpty()) {
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			menu.displayError("전체 입출고 내역 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void selectIn() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		
		try {
			list = new ProductIOService().selectIn();
			
			if (!list.isEmpty()) {
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			menu.displayError("입고 내역 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void selectOut() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		
		try {
			list = new ProductIOService().selectOut();
			
			if (!list.isEmpty()) {
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			menu.displayError("출고 내역 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void insertProductIn(ProductIO p) {
		int result;
		
		try {
			result = new ProductIOService().insertProductIn(p);
			if (result > 0) {
				new ProductMenu().displaySuccess("상품 입고 성공");
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 입고 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

	public void insertProductOut(ProductIO p) {
		int result;
		
		try {
			
			ArrayList<Product> pList = new ProductService().selectAll();
			
			boolean isFind = false;
			for (Product product : pList) {
				if (product.getProductId().equals(p.getProductId())) {
					isFind = true;
					
					if (product.getStock() < p.getAmount()) {
						throw new ProductException("출고하고자 하는 제품의 재고수량이 부족합니다.");
					}
				}
			}
			if(!isFind) {
				throw new ProductException("상품 조회 실패");
			}
			
			result = new ProductIOService().insertProductOut(p);
			if (result > 0) {
				new ProductMenu().displaySuccess("상품 출고 성공");
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 출고 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());
		}
	}

}
