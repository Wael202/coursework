package com.heriotwatt.ase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Utilities {
	List<String> orderList = new ArrayList<String>();
	TreeSet<String> TableNumList = new TreeSet<>();

	public TreeSet<Order> ReadOrdersListFile(String fileName) {

		TreeSet<Order> tree = new TreeSet<Order>();
		BufferedReader buff = null;
		String data[] = new String[2];
		int OrderId = 0;
		try {
			buff = new BufferedReader(new FileReader("files/OrdersList.csv"));
			String inputLine = buff.readLine(); // read first line
			inputLine = buff.readLine();
			while (inputLine != null) {
				// split line into parts
				data = inputLine.split(",");
				// create order object
				int qty = Integer.parseInt(data[2].trim());
				Order order = new Order(OrderId++, data[0].trim(), data[1].trim(), qty);
				// add to order list
				try {
					tree.add(order);
					TableNumList.add(data[0]);
					orderList.add( data[1].trim());
				} catch (Exception e) {
					System.out.println(e.getMessage());

				}
				// read next line
				inputLine = buff.readLine();

			}
			return tree;
		} // end try

		catch (FileNotFoundException e) {
			System.out.println("The file does exist");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				buff.close();
			} catch (IOException ioe) {
				// don't do anything
				return null;
			}
		}

	}

	public TreeSet<Menu> ReaMenuFile(String fileName) {

		TreeSet<Menu> menuTree = new TreeSet<Menu>();
		BufferedReader buff = null;
		String data[] = new String[2];
		int MenuId = 0;
		try {
			buff = new BufferedReader(new FileReader("files/MenuList.csv"));
			String inputLine = buff.readLine(); // read first line
			inputLine = buff.readLine();
			while (inputLine != null) {
				// split line into parts
				data = inputLine.split(",");
				// create menu object
				double price = Double.parseDouble(data[1].trim());
				Menu menu = new Menu(MenuId++, data[2].trim(), data[0].trim(), price);
				// add to Menu list
				try {
					menuTree.add(menu);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				// read next line
				inputLine = buff.readLine();

			} // end while
			return menuTree;
		} catch (FileNotFoundException e) {
			System.out.println("The file does exist");
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				buff.close();
			} catch (IOException ioe) {
				// don't do anything
				return null;
			}
		}

	}

	public void WriteOrderBillToTextFile(String fileName, OrderCollection oc) {

		ArrayList<OderPrice> list = new ArrayList<>();
		list = oc.getOrderList();
		double itemprice = 0;
		BufferedWriter writer = null;
		try {

			// File logFile = new File(fileName);
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("Order Bill");
			writer.newLine();
			writer.write("Table " + oc.getTableNum());
			writer.newLine();
			// String format = "\n%17s%16s\n";
			String emotryStr = "";
			// System.out.format(format, "test", 1);
			for (int i = 0; i < list.size(); i++) {
				itemprice = list.get(i).getQuantity() * list.get(i).getItemPrice();
				String s = list.get(i).getQuantity() + " *" + list.get(i).getItemPrice() + " = " + itemprice;
				writer.write(list.get(i).getName() + "\t" + s);
				writer.newLine();
			}
			writer.write("\t \t \t =====");
			emotryStr = "Total for this table ";
			writer.newLine();
			writer.write(emotryStr + "\t" + oc.getTotalBill());
			writer.newLine();
			emotryStr = "Discount ";
			writer.write(emotryStr + "\t \t" + oc.getDiscount());
			writer.newLine();
			emotryStr = "Discounted Total ";
			writer.write(emotryStr + "\t" + oc.getDiscountedTotal());
			writer.newLine();
			System.out.println("File written Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public void WriteDishesNotOrderedToTextFile(String fileName, ArrayList<String> dishesList) {

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("DISHES NOT ORDERED");
			writer.newLine();
			writer.write("====================");
			writer.newLine();

			for (int i = 0; i < dishesList.size(); i++) {
				writer.write(dishesList.get(i));
				writer.newLine();
			}
			System.out.println("File written Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public void PrintOrderBill(OrderCollection oc) {
		ArrayList<OderPrice> list = new ArrayList<>();
		list = oc.getOrderList();
		double itemprice = 0;
		System.out.format("\n%17s%16s\n", "Order Bill", "");
		System.out.println("Table " + oc.getTableNum());
		String format = "\n%17s%16s\n";
		String emotryStr = "";
		for (int i = 0; i < list.size(); i++) {
			itemprice = list.get(i).getQuantity() * list.get(i).getItemPrice();
			String s = list.get(i).getQuantity() + " *" + list.get(i).getItemPrice() + " = " + itemprice;
			System.out.format(format, list.get(i).getName(), s);
		}
		System.out.format(format, emotryStr, "=====");
		emotryStr = "Total for this table ";
		System.out.println(emotryStr + "\t      " + oc.getTotalBill());
		emotryStr = "Discount ";
		System.out.println(emotryStr + "\t              " + oc.getDiscount());
		emotryStr = "Discounted Total ";
		System.out.println(emotryStr + "\t      " + oc.getDiscountedTotal());
	}

	public void PrintMenuAndWriteToTextFile(TreeSet<Menu> menuSet, String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("\t MENU");
			writer.newLine();
			writer.write("======================================");
			writer.newLine();

			// ---------------------------------
			System.out.format("\n%17s%16s\n", "Menu", "");
			System.out.format("%17s%16s\n", "====", "\n");

			List<String> DishesNames = new ArrayList<String>();
			List<String> DishesNames2 = new ArrayList<String>();
			List<String> DishesNames3 = new ArrayList<String>();
			List<String> DishesNames4 = new ArrayList<String>();

			// menuSet=Utility.ReaMenuFile("files/MenuList.csv");

			for (Menu m : menuSet) {
				if (m.getCategory().equals("Starter")) {

					DishesNames.add(m.getDishName());
				}

				if (m.getCategory().equals("Main")) {

					DishesNames2.add(m.getDishName());
				}

				if (m.getCategory().equals("Desert")) {

					DishesNames3.add(m.getDishName());

				}

				if (m.getCategory().equals("Drink")) {

					DishesNames4.add(m.getDishName());

				}

			}

			Collections.sort(DishesNames);
			Collections.sort(DishesNames2);
			Collections.sort(DishesNames3);
			Collections.sort(DishesNames4);
			System.out.format("%20s%16s\n", "Starter", "\n");
			writer.write("Starter");
			writer.newLine();
			for (String temp : DishesNames) {
				for (Menu o : menuSet)
					if (o.getDishName().equals(temp)) {
						System.out.format("%32s%16s\n", temp, o.getPrice());
						writer.write("\t" + temp + "\t" + o.getPrice());
						writer.newLine();
					}
			}
			System.out.format("\n%20s%16s\n", "Main", "\n");
			writer.newLine();
			writer.write("Main");
			writer.newLine();
			for (String temp : DishesNames2) {
				for (Menu o : menuSet)
					if (o.getDishName().equals(temp)) {
						System.out.format("%32s%16s\n", temp, o.getPrice());
						writer.write("\t" + temp + "\t" + o.getPrice());
						writer.newLine();
					}
			}
			System.out.format("\n%20s%16s\n", "Desert", "\n");
			writer.newLine();
			writer.write("Desert");
			writer.newLine();
			for (String temp : DishesNames3) {
				for (Menu o : menuSet)
					if (o.getDishName().equals(temp)) {
						System.out.format("%32s%16s\n", temp, o.getPrice());
						writer.write("\t" + temp + "\t" + o.getPrice());
						writer.newLine();
					}
			}

			System.out.format("\n%20s%16s\n", "Drink", "\n");
			writer.newLine();
			writer.write("Drink");
			writer.newLine();
			for (String temp : DishesNames4) {
				for (Menu o : menuSet)
					if (o.getDishName().equals(temp)) {
						System.out.format("%32s%16s\n", temp, o.getPrice());
						writer.write("\t" + temp + "\t" + o.getPrice());
						writer.newLine();
					}
			}
			System.out.println("Menu File written Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}

	}

	public void PrintOrders(TreeSet<Order> orderSet) {

		// Check empty or not
		if (orderSet.isEmpty()) {
			System.out.print("There is no orders");
		} else {
			System.out.println("The order list contains : " + orderSet.size() +" orders");
		}
		System.out.println("The Orders List: ");
		System.out.println("Table ID \t  Dish Name \t Quantity");
		for (Order o : orderSet) {
			System.out.println(o.getId() + "\t" + o.getName() + "\t"+ o.getQuantity());
			orderList.add(o.getName());
		}

	}

	public void PrintFrecuancyOrdered() {
		System.out.println("\n FREQUENCY REPORT");
		Set<String> uniqueSet = new HashSet<String>(orderList);
		for (String temp : uniqueSet) {
			System.out.println(temp + ": " + Collections.frequency(orderList, temp));
		}
	}

	public void writeFrecuancyReport(String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write("\n FREQUENCY REPORT");
			writer.newLine();
			writer.write("====================");
			writer.newLine();
			Set<String> uniqueSet = new HashSet<String>(orderList);
			for (String temp : uniqueSet) {
				writer.write(temp + ": " + Collections.frequency(orderList, temp));
				writer.newLine();
			}
			System.out.println("FREQUENCY REPORT written Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public TreeSet<String> UniqeTableIDList() {
		// write here custom Exception
		if (TableNumList.size() > 0)
			return TableNumList;
		else
			return null;
	}

	public void WriteAllOrderBillToTextFile(String fileName, ArrayList<OrderCollection> ocArray) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for (OrderCollection oc : ocArray) {
				ArrayList<OderPrice> list = new ArrayList<>();
				list = oc.getOrderList();
				double itemprice = 0;
				writer.write("Order Bill");
				writer.newLine();
				writer.write("----------");
				writer.newLine();
				writer.write("Table " + oc.getTableNum());
				writer.newLine();
				// String format = "\n%17s%16s\n";
				String emotryStr = "";
				// System.out.format(format, "test", 1);
				for (int i = 0; i < list.size(); i++) {
					itemprice = list.get(i).getQuantity() * list.get(i).getItemPrice();
					String s = list.get(i).getQuantity() + " *" + list.get(i).getItemPrice() + " = " + itemprice;
					writer.write(list.get(i).getName() + "\t" + s);
					writer.newLine();
				}
				writer.write("\t \t \t =====");
				emotryStr = "Total for this table ";
				writer.newLine();
				writer.write(emotryStr + "\t" + oc.getTotalBill());
				writer.newLine();
				emotryStr = "Discount ";
				writer.write(emotryStr + "\t \t" + oc.getDiscount());
				writer.newLine();
				emotryStr = "Discounted Total ";
				writer.write(emotryStr + "\t" + oc.getDiscountedTotal());
				writer.newLine();
				writer.newLine();
				System.out.println("File written Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

}
