package com.heriotwatt.ase;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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

	public static void main(String arg[]) {

		Utilities Utility = new Utilities();
		orderSet = Utility.ReadOrdersListFile("files/OrdersList.csv");
		menuSet = Utility.ReaMenuFile("files/MenuList.csv");
		// Print Order List
		Utility.PrintOrders(orderSet);
		// Print Frecuancy Ordered List
		Utility.PrintFrecuancyOrdered();
		Utility.writeFrecuancyReport("files/FrecuancyReport.txt");
		// Print Menu
		Utility.PrintMenuAndWriteToTextFile(menuSet, "files/Menu.txt");
		// Print Bill
		OrdersAnalysis analysis = new OrdersAnalysis();
		OrderCollection oc = new OrderCollection();
		oc = analysis.getTableBill(orderSet, menuSet, "7", 0);
		Utility.PrintOrderBill(oc);
		String TableBilNumber = "TableBillNumber_" + oc.getTableNum() + ".txt";
		Utility.WriteOrderBillToTextFile("files/" + TableBilNumber, oc);
		// Write the Dishes Not Ordered
		ArrayList<String> dishesList = new ArrayList<>();
		dishesList = analysis.getDishesNotOrdered(orderSet, menuSet);
		if (dishesList.size() > 0) {
			Utility.WriteDishesNotOrderedToTextFile("files/DishesNotOrdered.txt", dishesList);
		}
		TreeSet<String> ts = new TreeSet<>();
		ArrayList<OrderCollection> arrayListOrderCollection = new ArrayList<>();
		ts = Utility.UniqeTableIDList();
		if (ts.size() > 0) {
			arrayListOrderCollection = analysis.getAllBills(orderSet, menuSet, ts, 0);
			System.out.println("Order Collection List size " + arrayListOrderCollection.size());
			for (OrderCollection c : arrayListOrderCollection) {
				Utility.WriteOrderBillToTextFile("files/AllBills_" + c.getOrderColectionId() + ".txt", c);
			}
			Utility.WriteAllOrderBillToTextFile("files/AllBills.txt", arrayListOrderCollection);
			
			  Double max = Double.MIN_VALUE;
			  String tableID="";
			  for (OrderCollection orc : arrayListOrderCollection) {
			   if (orc.getDiscountedTotal() > max) {
			    max = orc.getDiscountedTotal();
			    tableID=orc.getTableNum();
			   }
			  }
			  System.out.println("The Highest Bill is : " + max + "$  for Table Number : "+ tableID);

		}
		
	}
}
