package com.heriotwatt.ase;

import java.util.ArrayList;

public class OrderList {
    // Storage for an arbitrary collection of order.
    private ArrayList <Order> orderList;
    public OrderList()
    {
    	this.orderList = new ArrayList<Order>() ;
    }
    /**
     * Add a new set of order details to the order list
     * @param orderdetails The details of the order
     */
    public void addOrderDetails(Order orderdetails) 
    {
    	this.orderList.add(orderdetails);
    }

    
}
