package com.heriotwatt.ase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;

public class RestaurantFoodOrdersGUI {

	private JFrame frmRestaurantFoodOrders;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantFoodOrdersGUI window = new RestaurantFoodOrdersGUI();
					window.frmRestaurantFoodOrders.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RestaurantFoodOrdersGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRestaurantFoodOrders = new JFrame();
		frmRestaurantFoodOrders.setTitle("Restaurant Food Orders");
		frmRestaurantFoodOrders.setBounds(100, 100, 688, 688);
		frmRestaurantFoodOrders.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Label label = new Label("Restaurant Food Orders");
		label.setFont(new Font("Arial Black", Font.BOLD, 24));
		label.setAlignment(Label.CENTER);
		frmRestaurantFoodOrders.getContentPane().add(label, BorderLayout.NORTH);
	}

}
