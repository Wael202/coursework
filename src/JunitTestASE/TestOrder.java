package JunitTestASE;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.Test;
import com.heriotwatt.ase.*;

 
	public class TestOrder {
		
	/*
	 * Tests method to check the uniqueness of the dish name of order on a given table 
	 * this should fail as duplicate
	 */
	@Test
	public void UniqeOrder() {
		com.heriotwatt.ase.Utilities TestUtility = new com.heriotwatt.ase.Utilities();
		TreeSet<Order> tso= new TreeSet<Order>();
		tso = TestUtility.ReadOrdersListFile("files/OrdersList.csv");
		 
		 int quantity = 1;
		 String DishName = "Hamburger";
		 String TableNum = "2";
		 String message1 = "Failed Duplicate found";
		 String message2 = "Added no duplicate";
		 Order order = new Order(1, TableNum, DishName, quantity);
		 int orderSize = tso.size();
		 tso.add(order); 
		 int orderSize2 = tso.size(); 
		 if (orderSize == orderSize2) {
			 System.out.println(message2);
		 }
		 else {
			 System.out.println(message1);
		 }
		
	}

		
		/*
		 * Tests method to check the uniqueness of the dish name of order on a given table
		 * this should be added 
		 */
		@Test
		public void UniqeOrder2() {
			com.heriotwatt.ase.Utilities TestUtility = new com.heriotwatt.ase.Utilities();
			TreeSet<Order> tso= new TreeSet<Order>();
			tso = TestUtility.ReadOrdersListFile("files/OrdersList.csv");
			 
			 int quantity = 1;
			 String DishName = "Hamburger";
			 String TableNum = "1";
			 String message1 = "Failed Duplicate found";
			 String message2 = "Added no duplicate";
			 Order order = new Order(1, TableNum, DishName, quantity);
			 tso.add(order); 
			 Boolean exist = tso.contains(order); 
			 if (exist) {
				 System.out.println(message2);
			 }
			 else {
				 System.out.println(message1);
			 }
			
		}

		/*
		 * Tests method to check the dish name does not have any order
		 * this should have 
		 */
		@Test
		public void DishOrder() {
			com.heriotwatt.ase.Utilities TestUtility = new com.heriotwatt.ase.Utilities();
			OrdersAnalysis TestAnalysis = new OrdersAnalysis();
			
			String Category = "Main";
			String DishName = "Hamburger";
			String message1 = "Menu item not in order list";
			String message2 = "Menu item has order";
			
			TreeSet<Order> tso= new TreeSet<Order>();
			TreeSet<Menu> tsm = new TreeSet<Menu>();
			tso = TestUtility.ReadOrdersListFile("files/OrdersList.csv");
			tsm = TestUtility.ReaMenuFile("files/MenuList.csv");
			ArrayList<String> dishesList = new ArrayList<>();
			Menu m = new Menu(20, Category, DishName, 5);
			tsm.add(m);
			dishesList = TestAnalysis.getDishesNotOrdered(tso, tsm);
			if ( dishesList.contains(DishName)) {
				 System.out.println(message1);
	
			}	  
			  else {
				 System.out.println(message2);
			 }
			
		}

	
		/*
		 * Tests method to check the dish name does not have any order
		 * this should not have 
		 */
		@Test
		public void DishOrder2() {
			com.heriotwatt.ase.Utilities TestUtility = new com.heriotwatt.ase.Utilities();
			OrdersAnalysis TestAnalysis = new OrdersAnalysis();
			
			String Category = "Main";
			String DishName = "Shrimps";
			String message1 = "Menu item not in order list";
			String message2 = "Menu item has order";
			
			TreeSet<Order> tso= new TreeSet<Order>();
			TreeSet<Menu> tsm = new TreeSet<Menu>();
			tso = TestUtility.ReadOrdersListFile("files/OrdersList.csv");
			tsm = TestUtility.ReaMenuFile("files/MenuList.csv");
			ArrayList<String> dishesList = new ArrayList<>();
			Menu m = new Menu(20, Category, DishName, 5);
			tsm.add(m);
			dishesList = TestAnalysis.getDishesNotOrdered(tso, tsm);
			if ( dishesList.contains(DishName)) {
				 System.out.println(message1);
	
			}	  
			  else {
				 System.out.println(message2);
			 }
			
		}
		
	}
	
