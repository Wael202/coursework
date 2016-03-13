package com.heriotwatt.ase;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import com.heriotwatt.ase.Observer;
import com.heriotwatt.ase.Subject;

public class OrdersSimulation implements Subject{
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private  TreeSet<Order> orderTreeSet = new TreeSet<>();
	//private TreeSet<Menu> menuTreeSet = new TreeSet<>();
	Utilities Utility =new Utilities();	
	int KitchenOrderId = 1;
	String status = "new";
	TreeSet<KitchenOrders> KitchenOrdersList=new TreeSet<>();
	
	public int getKitchenOrderId() {
		return KitchenOrderId;
	}
	public void setKitchenOrderId(int kitchenOrderId) {
		KitchenOrderId = kitchenOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		notifyObservers();
	}
	public void notifyObservers() {
	//	System.out.println("Notifying to all the subscribers when product became available");
		 for (Observer ob : observers) {
             ob.update(this.status,"" );
      }

	}

	public void registerObserver(Observer observer) {
		 observers.add(observer);
		
	}

	public void removeObserver(Observer observer) {
		 observers.remove(observer);
		
	}

	// generate random KitchenOrder list of 10 orders
    public TreeSet<KitchenOrders> GenerateRandomOrders(){
    	TreeSet<Integer> selectedOrdersNumb =new TreeSet<>();
    	orderTreeSet = Utility.ReadOrdersListFile("files/OrdersList.csv");
    	 if(orderTreeSet.size()>0){
    		 selectedOrdersNumb=PickRandomOrdersID(orderTreeSet.size());
     		for(Integer i: selectedOrdersNumb){
     			for (Order o : orderTreeSet) {
     				if((o.getTableNum().equals("1")) || (o.getTableNum().equals("2"))){
     				if (i==o.getId()) {
     					
     					KitchenOrders kitchenOrder = new KitchenOrders(o.getId(), o.getTableNum(),o.getName(), o.getQuantity(), KitchenOrderId++, status);
     					KitchenOrdersList.add(kitchenOrder);
     					
     				}
     				}
     					
     			}
     		}
     		return KitchenOrdersList;

    	 }
    	 else
    		 return null;
    	 
    }
    public static TreeSet<Integer> PickRandomOrdersID(int size) {
        Random row = new Random();
      	 int number;
      	 int i=25;
      	 TreeSet<Integer> orderNumbers = new TreeSet<>();
      	 for (int numbers = 1; numbers < i; numbers++) {
      	 number = row.nextInt(size);
      	 if ((orderNumbers.contains(number) == false) && (number !=0))
      		orderNumbers.add(number);
      	 else
      	 i++;
      	 }
      	 return orderNumbers;
    }
    public static void ShowKitchenOrders(TreeSet<KitchenOrders> treeSetKitchenOrders){
		for(KitchenOrders ko: treeSetKitchenOrders){
			System.out.println("Kitchen Order Id " + ko.getKitchenOrderId() +" Item " + ko.getName() + " Qty "+ ko.getQuantity() + " Table " + ko.getTableNum());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}

}


