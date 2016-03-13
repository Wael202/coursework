package com.heriotwatt.ase;

import java.util.TreeSet;

public class KitchenOrders extends Order{
	
	private int  kitchenOrderId;
	private String  status;
	
	public int getKitchenOrderId() {
		return kitchenOrderId;
	}
	public void setKitchenOrderId(int kitchenOrderId) {
		this.kitchenOrderId = kitchenOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
	public KitchenOrders(int Id, String TableNum, String DishName, int Qty, int KitchenOrderId, String status) {
		super(Id, TableNum, DishName, Qty);
		// TODO Auto-generated constructor stub
		this.kitchenOrderId= KitchenOrderId;
		this.status = status;
	}	

}

