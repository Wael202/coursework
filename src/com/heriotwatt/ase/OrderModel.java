package com.heriotwatt.ase;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

// the Model class - her the random orders will be created
// the core simulator code 
public class OrderModel extends Observable implements Runnable {

	////////////////////
	public String KitchenOrderInprocess="";
	public KitchenOrders kitchenOrder;
	public OrderDelivery orderDeliveryTable1;
	public OrderDelivery orderDeliveryTable2;
	public boolean orderDeliveryTable1Notify=false;
	public boolean orderDeliveryTable2Notify=false;
	public String orderTable1String="";
	public String orderTable2String="";
	public ArrayList<Order> OrderListTable1=new ArrayList<>();
	public ArrayList<Order> OrderListTable2=new ArrayList<>();
	private  TreeSet<Order> orderTreeSet = new TreeSet<>();
	private TreeSet<Menu> menuTreeSet = new TreeSet<>();
	Utilities Utility =new Utilities();	
	// OrdersAnalysis is can Class contains method for Analyzing the orders and reports
	OrdersAnalysis analysis = new OrdersAnalysis();
	int KitchenOrderId = 1;
	String status = "New";
	 public TreeSet<KitchenOrders> KitchenOrdersList=new TreeSet<>();
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
      	 int i=20;
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
    
    public String OrderlistDetailsTable1( )
    {
    	StringBuffer allEntries = new StringBuffer();
        for(Order o : OrderListTable1) {
        	allEntries.append(o.getId() +"  " + o.getName() + " *  "+ o.getQuantity());
            allEntries.append('\n');

        }
        return allEntries.toString();
    }
    public String OrderlistDetailsTable2( )
    {
		try {
			Thread.sleep(3000);
			PrintOrderBill("1");
		} catch (InterruptedException e) {
			e.printStackTrace();
		
		// End to be deleted
		}
    	StringBuffer allEntries = new StringBuffer();
        for(Order o : OrderListTable2) {
        	allEntries.append(o.getId() +"  " + o.getName() + " *  "+ o.getQuantity());
            allEntries.append('\n');

        }
        return allEntries.toString();
    }
    public void PrintOrderBill(String tbID ){
    	double discountAmount = 10;
		OrderCollection oc = new OrderCollection();
		TreeSet<Order> orderSet;
		if(tbID.equals("1"))
			orderSet=new TreeSet<>(OrderListTable1);
		else if(tbID.equals("2"))
			orderSet=new TreeSet<>(OrderListTable2);
		else
			//Show MessageBox
			orderSet=null;
		menuTreeSet= Singleton.getInstance().ReaMenuFile("files/MenuList.csv");
		oc = analysis.getTableBill(orderSet, menuTreeSet, tbID, discountAmount);
		String TableBilNumber = "TableBillNumberMVC_" + oc.getTableNum() + ".txt";
		Singleton.getInstance().WriteOrderBillToTextFile("files/" + TableBilNumber, oc);
    }
    @Override
	public void run() {
 		TreeSet<Integer> selectedOrdersNumb =new TreeSet<>();
 	//	orderTreeSet = Utility.ReadOrdersListFile("files/OrdersList.csv");
 		orderTreeSet = Singleton.getInstance().ReadOrdersListFile("files/OrdersList.csv");
 		GenerateRandomOrdersList(selectedOrdersNumb,orderTreeSet);
	// Update the Order Status from New to In process
 		UpdateKitchenOrdersStatus("In Process");
 		DeliverOrderToTable("Delivered");
 		
		}
		
public synchronized void GenerateRandomOrdersList(TreeSet<Integer> _selectedOrdersNumber,TreeSet<Order> _orderTreeSet) {
	
	 if(_orderTreeSet.size()>0){
		 _selectedOrdersNumber=PickRandomOrdersID(_orderTreeSet.size());
 		for(Integer i: _selectedOrdersNumber){
 			for (Order o : _orderTreeSet) {
 				if((o.getTableNum().equals("1")) || (o.getTableNum().equals("2"))){
 				if(i==o.getId()){
 					 kitchenOrder = new KitchenOrders(o.getId(), o.getTableNum(),o.getName(), o.getQuantity(), KitchenOrderId++, status);
 				   KitchenOrdersList.add(kitchenOrder);
 					setChanged();
 					notifyObservers();
 			    	clearChanged();
 			    	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
 				}
 					
 			}
 			}
 		}
 		

	 }
	}
public synchronized void UpdateKitchenOrdersStatus(String Status){
	if(KitchenOrdersList.size()>0){
		for(KitchenOrders ko: KitchenOrdersList){
			ko.setStatus(Status);
		}
		
	}
	//To be deleted
	for(KitchenOrders ko: KitchenOrdersList){
		kitchenOrder=ko;
		  KitchenOrderInprocess="ID " + ko.getKitchenOrderId() +" Item " + ko.getName() + " Qty "+ ko.getQuantity() + " Table " + ko.getTableNum()+ " Status " + ko.getStatus()+ "\n";
			setChanged();
			notifyObservers();
	    	clearChanged();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// End to be deleted
	}
}





public synchronized void DeliverOrderToTable(String Status){
	

	if(KitchenOrdersList.size()>0){
		for(KitchenOrders ko: KitchenOrdersList){
			ko.setStatus(Status);
		}
		
	}
	

	for(KitchenOrders ko: KitchenOrdersList){
		kitchenOrder=ko;
		if(ko.getTableNum().equals("1")){
		Order orderTable1=new Order(ko.getId(),ko.getTableNum(),ko.getName(),ko.getQuantity());
		OrderListTable1.add(orderTable1);

		}
		else{
			Order orderTable2=new Order(ko.getId(),ko.getTableNum(),ko.getName(),ko.getQuantity());
			OrderListTable2.add(orderTable2);

		}
	}
	 
	 
	for(int i=0;i<1;i++){
		if(i==1){

		 orderDeliveryTable1=new OrderDelivery(i,OrderListTable1,Status,"Water1");

		 orderDeliveryTable1Notify=true;
		}
		if(i==2){
			
		 orderDeliveryTable2=new OrderDelivery(i,OrderListTable2,Status,"Water2");
		 orderDeliveryTable2Notify=true;
		}
			setChanged();
			notifyObservers();
	    	clearChanged();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		
		// End to be deleted
		}
	}
}


}
