package com.heriotwatt.ase;

public class OderPrice extends Order{
	private double itemPrice;
	
	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public OderPrice(int Id, String TableNum, String DishName, int Qty,double Itemprice) {
		super(Id, TableNum, DishName, Qty);
	this.itemPrice=Itemprice;
	}



	
	
}
