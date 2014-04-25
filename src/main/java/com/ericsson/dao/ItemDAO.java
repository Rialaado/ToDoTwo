
package com.ericsson.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.entities.User;
import com.ericsson.entities.Item;
import com.ericsson.interfaces.IUserDAO;
import com.ericsson.interfaces.IItemDAO;



public class ItemDAO implements IItemDAO{
	
	@PersistenceContext(unitName = "todoList")
	private EntityManager em;

	
	
	
	@Override
	public void addItem(Item entity) {
		try {
			em.persist(entity);
					
		} catch (NoResultException e) {
		
			System.out.println("CellHier was not created");
		}
	}

	@Override
	public void removeItem(Item entity) {
		boolean result = false;
		Item item = null;
	
		try {
			item = em.find(Item.class, entity.getItemID());
			result = true;
		} catch (NoResultException e) {
			result = false;
		}
		
		if(result = true && item != null){
			em.remove(entity);
		}
	}

	@Override
	public void updateItem(Item entity) {
		
		Item item = null;

		try {
			item = em.find(Item.class, entity.getItemID());
			if(item != null){
				em.merge(entity);
			}			
		} catch (NoResultException e) {
			System.out.println("CellHier was not updated");
		}
		
		
	}

	@Override
	public Item findItem(int itemID) {
		Item item = null;
		
		try {
			item =   em.find(Item.class, itemID);	
		} catch (NoResultException e) {
			item = null;
		}	
	
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAllItems() {
		List<Item> items = null;
		
		try {
			items = (List<Item>) em.createQuery("SELECT c FROM Item c");
		} catch (NoResultException e) {
			items = null;
		}
		
		return items;
	}

}
