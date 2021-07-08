package com.kh.model.vo;

import java.sql.Date;

public class ProductIO {
	private int ioNum;
	private String productId;
	private String pName; // 조인해서 받아올거
	private Date ioDate;
	private int amount;
	private String status;

	public ProductIO() {
	}

	public ProductIO(String productId, String pName, int amount, String status) {
		super();
		this.productId = productId;
		this.pName = pName;
		this.amount = amount;
		this.status = status;
	}
	
	public ProductIO(int ioNum, String productId, String pName, Date ioDate, int amount, String status) {
		super();
		this.ioNum = ioNum;
		this.productId = productId;
		this.pName = pName;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}

	public ProductIO(String productId, int amount, String status) {
		this.productId = productId;
		this.amount = amount;
		this.status = status;
	}

	public int getIoNum() {
		return ioNum;
	}

	public void setIoNum(int ioNum) {
		this.ioNum = ioNum;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ioNum + "\t" + String.format("%-8s\t", productId) + pName + "\t" + ioDate
				+ "\t" + amount + "\t" + status;
	}

}
