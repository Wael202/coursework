package com.heriotwatt.ase;

import java.util.Comparator;

public class Order implements Comparable<Order> {
	private int OrderId;
	private String TableNum;
	private String DishName;
	private int Quantity;

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return Quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Order(int Id, String TableNum, String DishName, int Qty) {
		this.OrderId = Id;
		this.TableNum = TableNum;
		this.DishName = DishName;
		this.Quantity = Qty;
	}

	public String getName() {
		return this.DishName;
	}

	public void setName(String name) {
		this.DishName = name;
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

	public int getId() {
		return this.OrderId;
	}

	public void setId(int id) {
		this.OrderId = id;
	}

	@Override
	public int compareTo(Order o) {
		String s1 = this.DishName;
		String s2 = o.DishName;
		String t1 = this.TableNum;
		String t2 = o.TableNum;
		int r1 = s1.compareTo(s2);
		int r2 = t1.compareTo(t2);
		if (r1 == 0 && r2 == 0)
			return 0;
		else
			return 1;
	}

}
