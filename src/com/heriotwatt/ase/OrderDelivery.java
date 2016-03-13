package com.heriotwatt.ase;



public class OrderDelivery implements Observer{
	private int Id;
	private int OrderId;
	private String TableNum;
	private String DishName;
	private int Quantity;
	private String OrderStatus;
	private String Water;
	
	public OrderDelivery(int id, int orderId, String tableNum, String dishName, int quantity, String orderStatus,
			String water) {

		Id = id;
		OrderId = orderId;
		TableNum = tableNum;
		DishName = dishName;
		Quantity = quantity;
		OrderStatus = orderStatus;
		Water = water;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public String getDishName() {
		return DishName;
	}
	public void setDishName(String dishName) {
		DishName = dishName;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public String getWater() {
		return Water;
	}
	public void setWater(String water) {
		Water = water;
	}
	
	public void update(String orderStaus,String WaterName) {
		// TODO Auto-generated method stub
		
	}
	


}
