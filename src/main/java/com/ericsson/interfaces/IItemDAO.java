package com.ericsson.interfaces;

import java.util.List;

import com.ericsson.entities.Item;



public interface IItemDAO {
	
	public String addItem(Item entity);
	
	public void removeItem(Item entity);
	
	public void updateItem(Item entity);
	
	public Item findItem(int itemID);
	
	public List<Item> findAllItems();
	
	public List<Item> findAllItemsForUser(String username);

}
