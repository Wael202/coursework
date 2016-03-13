package com.heriotwatt.ase;

import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

// the Model class - her the random orders will be created
// the core simulator code 
public class OrderModel extends Observable implements Runnable {

	////////////////////
	private  TreeSet<Order> orderTreeSet = new TreeSet<>();
	//private TreeSet<Menu> menuTreeSet = new TreeSet<>();
	Utilities Utility =new Utilities();	
	int KitchenOrderId = 1;
	String status = "new";
	TreeSet<KitchenOrders> KitchenOrdersList=new TreeSet<>();
	/*
	 * // constructor 
	 */
		public OrderModel() {
			//KitchenOrdersList = GenerateRandomOrders();
			//ShowKitchenOrders(KitchenOrdersList);
		}

	// generate random KitchenOrder list of 10 orders
    public TreeSet<KitchenOrders> GenerateRandomOrders(){
    	TreeSet<Integer> selectedOrdersNumb =new TreeSet<>();
    	orderTreeSet = Utility.ReadOrdersListFile("files/OrdersList.csv");
    	 if(orderTreeSet.size()>0){
    		 selectedOrdersNumb=PickRandomOrdersID(orderTreeSet.size());
     		for(Integer i: selectedOrdersNumb){
     			for (Order o : orderTreeSet) {
     				if(i==o.getId()){
     					KitchenOrders kitchenOrder = new KitchenOrders(o.getId(), o.getTableNum(),o.getName(), o.getQuantity(), KitchenOrderId++, status);
     					KitchenOrdersList.add(kitchenOrder);
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
      	 int i=6;
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
    
    public String listDetails( )
    {
    	StringBuffer allEntries = new StringBuffer();
        for(KitchenOrders ko : GenerateRandomOrders()) {
            allEntries.append("Kitchen Order Id " + ko.getKitchenOrderId() +" Item " + ko.getName() + " Qty "+ ko.getQuantity() + " Table " + ko.getTableNum());
            allEntries.append('\n');
        }
        return allEntries.toString();
    }
    
	@Override
	public void run() {
		 
		listDetails();
			
		}
		
public synchronized void startOrders(Order o) {
		
		
		//update view display
		setChanged();
		notifyObservers();
    	clearChanged();

	}

}
