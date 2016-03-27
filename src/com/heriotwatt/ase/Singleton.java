package com.heriotwatt.ase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Singleton {
    private static final Singleton instance= new Singleton();
    private Singleton() {
   
    }

    public synchronized void writeToFile(String str) {
        // Do whatever
    }

    public static Singleton getInstance() {
        return instance;
    }
	public synchronized void WriteOrderBillToTextFile(String fileName, OrderCollection oc) {

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
			System.out.println("TableBillNumber.txt File written Successfully");
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


	public synchronized TreeSet<Order> ReadOrdersListFile(String fileName) {

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
				//	TableNumList.add(data[0]);
				//	orderList.add( data[1].trim());
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
	public synchronized TreeSet<Menu> ReaMenuFile(String fileName) {

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



}
