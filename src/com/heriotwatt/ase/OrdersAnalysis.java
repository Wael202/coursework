package com.heriotwatt.ase;

import java.util.ArrayList;
import java.util.TreeSet;

public class OrdersAnalysis {
	TreeSet<String> TableNumList = new TreeSet<>();
	OrderCollection orderCollection = new OrderCollection();
	private ArrayList<OrderCollection> orderColl;
	int OrderColID = 1;
	String tableNum;
	double Price;
	double OrderTotal = 0;
	double DiscountedTotal = 0;

	public OrderCollection getTableBill(TreeSet<Order> orderTreeSet, TreeSet<Menu> menuTreeSet, String tableNum,
			double discount) {
		if ((orderTreeSet != null) && (menuTreeSet != null)) {
			ArrayList<OderPrice> ol = new ArrayList<>();

			for (Order o : orderTreeSet) {
				if (o.getTableNum().equals(tableNum)) {
					tableNum = o.getTableNum();

					// get price from menuTreeSet
					for (Menu m : menuTreeSet) {
						if (m.getDishName().equals(o.getName())) {
							Price = m.getPrice();
						}
					}
					OderPrice orderObj = new OderPrice(o.getId(), o.getTableNum(), o.getName(), o.getQuantity(), Price);
					ol.add(orderObj);

				}

			}
			for (OderPrice op : ol) {
				OrderTotal += op.getItemPrice() * op.getQuantity();
			}
			if (discount != 0) {
				DiscountedTotal = OrderTotal - (OrderTotal * discount);
			} else {
				DiscountedTotal = OrderTotal;
			}
			orderCollection.setOrderColectionId(OrderColID++);
			orderCollection.setOrderList(ol);
			orderCollection.setTableNum(tableNum);
			orderCollection.setTotalBill(OrderTotal);
			orderCollection.setDiscountedTotal(DiscountedTotal);

		}
		return orderCollection;
		// PrintOrderBill();
	}

	public ArrayList<String> getDishesNotOrdered(TreeSet<Order> orderTreeSet, TreeSet<Menu> menuTreeSet) {
		boolean dishNameExist = false;
		ArrayList<String> dishesNotOrderedList = new ArrayList<>();
		// dishesNotOrderedList.add("Empty");
		if ((orderTreeSet != null) && (menuTreeSet != null)) {
			for (Menu m : menuTreeSet) {
				for (Order o : orderTreeSet) {
					String mDishName = m.getDishName().trim();
					String oDishName = o.getName().trim();
					if (mDishName.equals(oDishName)) {
						dishNameExist = true;
						break;
					}

					else {
						dishNameExist = false;
					}
				}
				if (dishNameExist == false) {
					dishesNotOrderedList.add(m.getDishName());
				}
			}
		}
		return dishesNotOrderedList;

	}

	public ArrayList<OrderCollection> getAllBills(TreeSet<Order> orderTreeSet, TreeSet<Menu> menuTreeSet,
			TreeSet<String> tableNumArray, double discount) {
		String tbNumber = "";

		ArrayList<OrderCollection> ocList = new ArrayList<>();
		int orderColId = 1;
		if ((orderTreeSet != null) && (menuTreeSet != null)) {

			for (String tableId : tableNumArray) {
				double price = 0;
				double ordertotal = 0;
				double discountedtotal = 0;
				ArrayList<OderPrice> ol = new ArrayList<>();
				OrderCollection orderCol = new OrderCollection();
				for (Order o : orderTreeSet) {
					if (o.getTableNum().equals(tableId)) {
						tbNumber = o.getTableNum();

						// get price from menuTreeSet
						for (Menu m : menuTreeSet) {
							if (m.getDishName().equals(o.getName())) {
								price = m.getPrice();
							}

						}
						OderPrice orderObj = new OderPrice(o.getId(), o.getTableNum(), o.getName(), o.getQuantity(),
								price);
						ol.add(orderObj);

					}

				}
				for (OderPrice op : ol) {
					ordertotal += op.getItemPrice() * op.getQuantity();
				}
				if (discount != 0) {
					discountedtotal = ordertotal - (ordertotal * discount);
				} else {
					discountedtotal = ordertotal;
				}
				orderCol.setOrderColectionId(orderColId++);
				orderCol.setOrderList(ol);
				orderCol.setTableNum(tbNumber);
				orderCol.setTotalBill(ordertotal);
				orderCol.setDiscountedTotal(discountedtotal);
				ocList.add(orderCol);

			}

		}
		return ocList;
		// PrintOrderBill();
	}
}