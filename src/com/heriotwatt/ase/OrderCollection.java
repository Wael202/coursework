package com.heriotwatt.ase;

import java.util.ArrayList;
import java.util.List;

public class OrderCollection {
	private int OrderColectionId;
	private String TableNum;
	private ArrayList<OderPrice> OrderList;
	private double TotalBill;
	private double Discount;
	private double DiscountedTotal;
	// private double Price ;



	public OrderCollection() {
		OrderList = new ArrayList<OderPrice>();
	}

	/**
	 * @return the orderColectionId
	 */
	public int getOrderColectionId() {
		return OrderColectionId;
	}

	/**
	 * @param orderColectionId
	 *            the orderColectionId to set
	 */
	public void setOrderColectionId(int orderColectionId) {
		OrderColectionId = orderColectionId;
	}

	/**
	 * @return the tableNum
	 */
	public String getTableNum() {
		return TableNum;
	}

	/**
	 * @param tableNum
	 *            the tableNum to set
	 */
	public void setTableNum(String tableNum) {
		TableNum = tableNum;
	}

	/**
	 * @return the orderList
	 */
	public ArrayList<OderPrice> getOrderList() {
		return OrderList;
	}

	/**
	 * @param orderList
	 *            the orderList to set
	 */
	public void setOrderList(ArrayList<OderPrice> orderList) {
		OrderList = orderList;
	}

	/**
	 * @return the totalBill
	 */
	public double getTotalBill() {
		return TotalBill;
	}

	/**
	 * @param totalBill
	 *            the totalBill to set
	 */
	public void setTotalBill(double totalBill) {
		TotalBill = totalBill;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return Discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(double discount) {
		Discount = discount;
	}

	/**
	 * @return the discountedTotal
	 */
	public double getDiscountedTotal() {
		return DiscountedTotal;
	}

	/**
	 * @param discountedTotal
	 *            the discountedTotal to set
	 */
	public void setDiscountedTotal(double discountedTotal) {
		DiscountedTotal = discountedTotal;
	}

}
