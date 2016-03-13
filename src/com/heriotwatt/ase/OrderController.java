package com.heriotwatt.ase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

//The Controller coordinates interactions
// between the View and Model

public class OrderController {

private OrderView thisview;
private OrderModel thismodel;
	
	public OrderController (OrderView v, OrderModel m) {
		this.thisview = v;
		this.thismodel = m;
		
		// Tell the View that when ever the random orders generated 
		// to execute the action method
		thisview.addOrderListener(new OrdersController());
		
	}
	
	class OrdersController  implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	//thisview.disableProcessButton();
    		Thread thread = new Thread (thismodel);
    		thread.start();
    		
       // 	TreeSet<KitchenOrders> KitchenOrdersList=new TreeSet<>();
        //	KitchenOrdersList = thismodel.GenerateRandomOrders();
        	
        }
    }
    

}
