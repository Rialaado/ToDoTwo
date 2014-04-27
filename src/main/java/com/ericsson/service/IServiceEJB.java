package com.ericsson.service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.jws.WebService;

import com.ericsson.entities.Item;
import com.ericsson.entities.User;
import com.ericsson.other.Top10Failure;

@Local
@WebService
public interface IServiceEJB {

	public String addNewUser(User entity);
	
	public List<Item> retrieveAllItems();

	public User searchforAccountUsernameAndPassword(String username, String password);

	public List<Item> searchforUserItems(String accountName);
	
	public String addNewitem(Item item);
	
	public User searchforAccountByUsername(String username);

}
