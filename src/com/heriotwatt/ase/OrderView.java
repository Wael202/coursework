package com.heriotwatt.ase;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


//This is the View
// Its only job is to display what the user sees

public class OrderView extends JFrame implements Observer {
	private int numOrders;
	private OrderModel orderModel;
	JButton processButton;
    private JTextArea [] orders ;
    
	
	public OrderView(OrderModel orderModel) {
		this.orderModel=orderModel;	
		numOrders = orderModel.KitchenOrdersList.size();
		orderModel.addObserver(this);
		//set up window title
        setTitle("Order Simulator");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
      //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createHeaderPanel(), BorderLayout.NORTH);
        contentPane.add(createOrderPanel(), BorderLayout.CENTER);
  //    contentPane.add(createKitchenOrderPanel(), BorderLayout.CENTER);
        //pack and set visible
        pack();
        setVisible(true);   	
	}
		
	// create the Orders Panel and view its content 
	private JPanel createOrderPanel() {
		
    	JPanel orderPanel = new JPanel(new GridLayout (2,2));
		orders  = new JTextArea [4];
		for (int i = 0; i < 4; i++) {
			orders [i]= new JTextArea(20,60);
			//tabular layout (taken from the provided Auction Sample Code)
			orders[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
			orders [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			orderPanel.add(orders[i]);
		}
		
		/* 
		 
    		String report = orderModel.listDetails() ;
			this.orders[i].setText( report);	
			if (report.contains("Table 2")){
				orders[i].setForeground(Color.BLUE);
			}
			else
				orders[i].setForeground(Color.BLACK);
    	}
*/		
		return orderPanel;
    }
	
	
	 private JPanel createHeaderPanel() {
	        //north panel shows the button to start processing
	        processButton = new JButton("Start Simulation");
	        JPanel northPanel = new JPanel();
	        northPanel.add(processButton);
	        return northPanel;
	    }
	    
	    //MVC pattern - allows listeners to be added
	    public void addOrderListener(ActionListener al) {
	        processButton.addActionListener(al);
	    }
	    
	    public void disableProcessButton() {
	    	processButton.setEnabled(false);
	    }
	    /////////////////////////////////////////////////////////
	    
	    public synchronized void update(Observable o, Object args) {
	    	
	    	String report="";
	    if(orderModel.kitchenOrder.getStatus().equals("New")){
	    	report="Kitchen Order Id " + orderModel.kitchenOrder.getKitchenOrderId()+" Item " +  orderModel.kitchenOrder.getName() + " Qty "+  orderModel.kitchenOrder.getQuantity() + " Table " +  orderModel.kitchenOrder.getTableNum() + "\n";
	    	this.orders[0].append(report);
	    	orders[0].setForeground(Color.DARK_GRAY);
	    }
	    	
	    	  if(orderModel.kitchenOrder.getStatus().equals("In Process")){
	    		//  this.orders[0].setText("");
	    		  this.orders[2].append(orderModel.KitchenOrderInprocess);
	    		  orders[2].setForeground(Color.RED);
	    	  }
	    	  if((orderModel.kitchenOrder.getStatus().equals("Delivered")) && (orderModel.orderDeliveryTable1Notify==false)){
	    		  report="Table 1 \n";
	    		  report=report + orderModel.OrderlistDetailsTable1();
	    		//  this.orders[0].setText("");
	    		  this.orders[1].append(report);
	    		  orders[1].setForeground(Color.BLUE);
	    	  }
	    	  if((orderModel.kitchenOrder.getStatus().equals("Delivered")) && (orderModel.orderDeliveryTable2Notify==false)){
	    		  report="Table 2 \n";
	    		  report=report + orderModel.OrderlistDetailsTable2();
	    		//  this.orders[0].setText("");
	    		  this.orders[3].append(report);
	    		  orders[3].setForeground(Color.BLUE);
	    	  }
/*	    	for(KitchenOrders ko: orderModel.KitchenOrdersList){
    			report="Kitchen Order Id " + ko.getKitchenOrderId() +" Item " + ko.getName() + " Qty "+ ko.getQuantity() + " Table " + ko.getTableNum() + "\n";
    			this.orders[0].append(report);
    			}*/
/*	    	for (int i = 1; i < 4; i++) {	    		
				this.orders[i].setText( report);	
				if (report.contains("Table 2")){
					orders[i].setForeground(Color.BLUE);
				
				}
				else
					orders[i].setForeground(Color.BLACK);
	    	}*/
	    }
}
