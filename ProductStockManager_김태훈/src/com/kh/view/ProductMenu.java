package com.kh.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.controller.ProductIOController;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductMenu {

	private static Scanner sc = new Scanner(System.in);
	private ProductController pController = new ProductController();
	private ProductIOController pIOController = new ProductIOController();
	
	public void mainMenu() {
		
		int choice;
		
		do {
			System.out.println("======= 상품 관리 프로그램 =======");
			System.out.println("1. 상품 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			System.out.println("5. 상품 검색");
			System.out.println("6. 상품 입출고 메뉴");
			System.out.println("9. 프로그램 종료하기");
			System.out.print("번호 선택 : ");
			
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				pController.selectAll();
				break;
			case 2:
				pController.insertProduct(inputProduct());
				break;
			case 3:
				pController.updateProduct(updateProduct());
				break;
			case 4:
				pController.deleteProduct(inputProductId());
				break;
			case 5:
				pController.selectName(inputProductName());
				break;
			case 6:
				productIOMenu();
				break;
			case 9:
				System.out.print("프로그램을 종료하시겠습니까 (y/n)");
				if ('y' == sc.next().toLowerCase().charAt(0)) {
					pController.exitProgram();
					return;
				}
				break;
			default:
				System.out.println("번호를 잘못 입력하였습니다.");
				break;
			}
			
		} while (true);
	}

	private void productIOMenu() {
		int choice;
		
		do {
			System.out.println("=== 입출고 메뉴 ===");
			System.out.println("1. 전체 입출고 내역 조회");
			System.out.println("2. 입고 내역 조회");
			System.out.println("3. 출고 내역 조회");
			System.out.println("4. 상품 입고");
			System.out.println("5. 상품 출고");
			System.out.println("9. 메인 메뉴로 돌아가기");
			System.out.print("번호 선택 : ");
			
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				pIOController.selectAllIO();
				break;
			case 2:
				pIOController.selectIn();
				break;
			case 3:
				pIOController.selectOut();
				break;
			case 4:
				pIOController.insertProductIn(inputProductIn());
				break;
			case 5:
				pIOController.insertProductOut(inputProductOut());
				break;
			case 9:
				return;

			default:
				System.out.println("번호를 잘못 입력하였습니다.");
				break;
			}
		} while (true);
	}

	private ProductIO inputProductOut() {
		String productId;
		int amount;
		String status = "출고";
		
		System.out.println("출고할 상품 정보를 입력하세요 >> ");
		System.out.print("상품ID : ");
		productId = sc.next();
		System.out.print("출고수량 : ");
		amount = sc.nextInt();
		
		return new ProductIO(productId, amount, status);
	}

	private ProductIO inputProductIn() {
		String productId;
		int amount;
		String status = "입고";
		
		System.out.println("입고할 상품 정보를 입력하세요 >> ");
		System.out.print("상품ID : ");
		productId = sc.next();
		System.out.print("입고수량 : ");
		amount = sc.nextInt();
		
		return new ProductIO(productId, amount, status);
	}

	private String inputProductName() {
		System.out.print("조회할 상품 이름 입력 : ");
		return sc.next();
	}

	private String inputProductId() {
		System.out.print("조회할 상품 아이디 입력 : ");
		return sc.next();
	}

	private Product updateProduct() {
		Product p = new Product();
		
		System.out.println("수정할 상품 정보를 입력하세요 >> ");
		System.out.print("상품ID : ");
		p.setProductId(sc.next());
		System.out.print("상품명 : ");
		p.setpName(sc.next());
		System.out.print("가격 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("부가설명 : ");
		p.setDescription(sc.nextLine());
		System.out.print("재고수량 : ");
		p.setStock(sc.nextInt());
		
		return p;
	}

	private Product inputProduct() {
		Product p = new Product();
		
		System.out.println("새로운 상품 정보를 입력하세요 >> ");
		System.out.print("상품ID : ");
		p.setProductId(sc.next());
		System.out.print("상품명 : ");
		p.setpName(sc.next());
		System.out.print("가격 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("부가설명 : ");
		p.setDescription(sc.nextLine());
		System.out.print("재고수량 : ");
		p.setStock(sc.nextInt());
		
		return p;
	}

	public void displayProductList(List<Product> list) {
		System.out.println("========== 상품 리스트 ===========");
		System.out.println("\n상품ID\t\t상품명\t가격\t부가설명\t\t재고수량");

		for (Product product : list) {
			System.out.println(product);
		}
	}

	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패 : " + message);
	}

	public void displayProduct(Product p) {
		System.out.println("\n========== 조회된 상품 ===========");
		System.out.println("\n상품ID\t상품명\t가격\t부가설명\t재고수량");

		System.out.println(p);
	}

	public void displaySuccess(String message) {
		System.out.println("서비스 요청 결과 : " + message);
	}

	public void displayNoData() {
		System.out.println("조회된 데이터가 없습니다.");
	}
	

	public void displayProductList(ArrayList<ProductIO> list) {
		System.out.println("==================== 입출고 리스트 =====================");
		System.out.println("\n입출고번호\t상품ID\t\t상품명\t입출고일\t\t입출고수량\t입출고상태");

		for (ProductIO product : list) {
			System.out.println(product);
		}
	}

}
