package com.heriotwatt.ase;
import java.util.Comparator;

public class Menu implements Comparable<Menu>{
	private int MenuId;
    private String Category;
    private String DishName;
    private double  Price ;
    
    
    public Menu(int Id,String Category, String DishName,double Price){
    	this.MenuId=Id;
    	this.Category=Category;
    	this.DishName=DishName;
    	this.Price=Price;
    }
    
    public String getCategory() {
		return this.Category;
	}
    
    public String getDishName() {
		return this.DishName;
	}	
	
    public double  getPrice() {
		return this.Price;
	}	
    
	public int getId() {
		return this.MenuId;
	}
	public void setId(int id) {
		this.MenuId = id;
	}
	

	@Override
	public int compareTo(Menu o) {	
	String s1=this.DishName;
	String s2=o.DishName;
	int r1=s1.compareTo(s2);
	if(r1==0)
		return 0;
	else	
		return 1;
	}
		




}
