package com.heriotwatt.ase;

import java.util.ArrayList;

public class OrderDelivery {
	private int OrderDeliveryId;
	private ArrayList<Order> OrderList;
	private String OrderTableStatus;
	private String Water;
	private String orderListString;
	

	public OrderDelivery(int id,ArrayList<Order> orderlist,String orderstatus,String water) {

		OrderDeliveryId = id;
		OrderList=new ArrayList<Order>();
		OrderTableStatus = orderstatus;
		Water = water;
		
	}
	public String getOrderListString() {
		return orderListString;
	}
	public void setOrderListString(String orderListString) {
		this.orderListString = orderListString;
	}
	public int getOrderDeliveryId() {
		return OrderDeliveryId;
	}
	public void setOrderDeliveryId(int _OrderDeliveryId) {
		OrderDeliveryId = _OrderDeliveryId;
	}
	public String getWater() {
		return Water;
	}
	public void setWater(String water) {
		Water = water;
	}
	public ArrayList<Order> getOrderList() {
		return OrderList;
	}
	public void setOrderList(ArrayList<Order> orderList) {
		OrderList = orderList;
	}
	public String getOrderTableStatus() {
		return OrderTableStatus;
	}
	public void setOrderTableStatus(String orderTableStatus) {
		OrderTableStatus = orderTableStatus;
	}

/*public String getOrderListString1(ArrayList<Order> ol){
	StringBuffer allEntries = new StringBuffer();
    for(Order o : ol) {
        allEntries.append(o.getId() +"  " + o.getName() + " *  "+ o.getQuantity());
        allEntries.append('\n');

    }
    return allEntries.toString();
	
}
*/
}
