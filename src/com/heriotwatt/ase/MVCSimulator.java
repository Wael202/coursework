package com.heriotwatt.ase;

public class MVCSimulator {

	public static void main (String arg[])  {
    	OrderModel model = new OrderModel();   //the model
        OrderView  view  = new OrderView(model);
        OrderController controller = new OrderController(view, model);   
        view.setVisible(true);

    }
}

