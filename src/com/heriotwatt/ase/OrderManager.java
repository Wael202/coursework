package com.heriotwatt.ase;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Formatter;

public class OrderManager {
	// Global Variables
	public static TreeSet<Order> orderSet = new TreeSet<>();
	public static TreeSet<Menu> menuSet = new TreeSet<>();

	public static void main(String arg[])  {
		//Utilities is a Class contains all operational methods such as read and write files
		Utilities Utility = new Utilities();
		// OrdersAnalysis is can Class contains method for Analyzing the orders and reports
		OrdersAnalysis analysis = new OrdersAnalysis();
		// ts is Tree set contains unique tables ID
		TreeSet<String> ts = new TreeSet<>();
		//arrayListOrderCollection ArrayList contains a collection of orders and the calculation needed 
		ArrayList<OrderCollection> arrayListOrderCollection = new ArrayList<>();
		// Variables 
		Scanner in = new Scanner(System.in);
		char choice;
		String tbID = "";
		String hasDiscount = "n";
		double discountAmount = 0;
		String response = "";
		//Read the order list from csv file and store the data into TreeSet called orderSet
		orderSet = Utility.ReadOrdersListFile("files/OrdersList.csv");
		//Read the m list from csv file and store the data into TreeSet called orderSet
		menuSet = Utility.ReaMenuFile("files/MenuList.csv");
		//load the unique table ID's into the TreeSet(ts)
		ts = Utility.UniqeTableIDList();
		// Print Order List
		// Utility.PrintOrders(orderSet);
		do {
			System.out.println("|--------------------------------------------------------------|");
			System.out.println("|   Select from the choices below                              |");
			System.out.println("|--------------------------------------------------------------|");
			System.out.println("| #        |         Description                               |");
			System.out.println("|--------------------------------------------------------------|");
			System.out.println("| 1        | Print Menu                                        |");
			System.out.println("| 2        | Frequancy Report                                  |");
			System.out.println("| 3        | Dishes Not Ordered                                |");
			System.out.println("| 4        | Table Bill                                        |");
			System.out.println("| 5        | Print All Table Bill Individually                 |");
			System.out.println("| 6        | Print The Highest Bill Amount                     |");
			System.out.println("|--------------------------------------------------------------|");
			System.out.println();
			System.out.println("Enter choice");

			// user choice here
			choice = in.next().charAt(0);
			switch (choice) {
			case '1':
				// Print Menu
				Utility.PrintMenuAndWriteToTextFile(menuSet, "files/Menu.txt");
				break;
			case '2':
				// Print Frequency Ordered List
				Utility.PrintFrecuancyOrdered();
				Utility.writeFrecuancyReport("files/FrecuancyReport.txt");
				break;
			case '3':
				// Write the Dishes Not Ordered
				ArrayList<String> dishesList = new ArrayList<>();
				dishesList = analysis.getDishesNotOrdered(orderSet, menuSet);
				if (dishesList.size() > 0) {
					Utility.WriteDishesNotOrderedToTextFile("files/DishesNotOrdered.txt", dishesList);
				}
				break;
			case '4':
				// Table Bill
				boolean tbIDexists =false;
				do{
					System.out.println("Enter Table ID : ");
					tbID = in.next();
					tbIDexists= ts.contains(tbID);
					
					if (tbIDexists) {
						System.out.println("Does this Table has discount?(y/n) : ");
						hasDiscount = in.next();
						if (hasDiscount.contentEquals("y")) {
						System.out.println("Enter the dicount % : ");
							discountAmount = in.nextDouble();
						} else
							discountAmount = 0;

						OrderCollection oc = new OrderCollection();
						oc = analysis.getTableBill(orderSet, menuSet, tbID, discountAmount);
						Utility.PrintOrderBill(oc);
						String TableBilNumber = "TableBillNumber" + oc.getTableNum() + ".txt";
						Utility.WriteOrderBillToTextFile("files/" + TableBilNumber, oc);
					} else
						System.out.println("The Table ID : " + tbID + " NOT exist in the orders list, Enter valid table ID " + ts.toString());	
			} while (!tbIDexists);
				break;
			case '5':
				// Print All Table Bill Individually

				if (ts.size() > 0) {
					arrayListOrderCollection = analysis.getAllBills(orderSet, menuSet, ts, 0);
					System.out.println("Order Collection List size " + arrayListOrderCollection.size());
					for (OrderCollection c : arrayListOrderCollection) {
						Utility.WriteOrderBillToTextFile("files/BillForTableNum" + c.getOrderColectionId() + ".txt", c);
					}
					Utility.WriteAllOrderBillToTextFile("files/AllBillsForAllTables.txt", arrayListOrderCollection);
				}
				break;
			case '6':
				// Print The Highest Bill Amount
				Double max = Double.MIN_VALUE;
				String tableID = "";
				for (OrderCollection orc : arrayListOrderCollection) {
					if (orc.getDiscountedTotal() > max) {
						max = orc.getDiscountedTotal();
						tableID = orc.getTableNum();
					}
				}
				System.out.println("The Highest Bill Amount is : " + max + "$  for Table Number : " + tableID);
				break;
			default:
				System.out.println("Invalid selection");
			}

			String message = "";
			try {
			System.out.println("Do you want to continue? (y/n)");
			response = in.next();
			response=getResponse(response);
			}
			catch (SelectionException e) {
				message = e.getMessage() + "\nNot valid entery please enter y or n"; 
				System.out.println(message);
				System.out.println("Do you want to continue? (y/n)");
				response = in.next();
			}
		
		} while (response.equalsIgnoreCase("y"));

	}

	public static String getResponse(String res) throws SelectionException {
    	
    	if ( (res.equalsIgnoreCase("y")) || (res.equalsIgnoreCase("n")) ) {
    		return res;
    	}
    	else
    		throw new SelectionException(res);
    }


}
