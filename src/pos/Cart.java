package pos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> itemList=new ArrayList<Item>();
	private ArrayList<ArrayList<Integer>>itemArray=new ArrayList<ArrayList<Integer>>();
	private double cartTotal;
	
	public Cart() {
		
		cartTotal=0.0;
	}
	public void populate(File files) throws ClassNotFoundException, IOException 
	{

		if (!(files.isDirectory())) {
			System.out.println("No Directory Found");
		}
		else {
			File[] itemFiles=files.listFiles();
			for(File file: itemFiles) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			itemList = (ArrayList<Item>)ois.readObject();
			ois.close();
		}
		}
	}
	public void addItemCart(Item item, int amount) {
		if(itemArray.get(0).contains(item.getItemNumber())) {//If item already exists in cart will add amount to it
			int itemLocation=itemArray.get(0).indexOf(item.getItemNumber());
			itemArray.get(1).add(itemLocation,itemArray.get(1).get(itemLocation)+amount);
		}
		else//Just adds the item to cart
		{
			itemArray.get(0).add(item.getItemNumber());
			itemArray.get(1).add(amount);
		}
		cartTotal+=amount*item.getCost();

	}
	public Item findItem(int itemNumber) {
		Item itemFound= new Item();
		for(Item item :itemList) {
			if(item.getItemNumber()==itemNumber) {
				itemFound=item;
			}
		}
		return itemFound;
	}
	public void removeFromCart(int itemNumber) {
		if(itemArray.get(0).contains(itemNumber)) {
			int i=itemArray.get(0).indexOf(itemNumber);
			itemArray.get(0).remove(i);
			Item item=findItem(itemNumber);
			cartTotal-=item.getCost()*itemArray.get(1).get(i);
			itemArray.get(1).remove(i);
		}
	}
	
}
