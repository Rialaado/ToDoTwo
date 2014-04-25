
package com.ericsson.dao;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ericsson.entities.User;
import com.ericsson.entities.Item;
import com.ericsson.interfaces.IUserDAO;

@Default
public class UserDAO implements IUserDAO{

	@PersistenceContext(unitName = "todoList")
	private EntityManager em;
	
	@Override
	public String addUser(User entity) {
		 
		User user = null;
		try {
			Query query = em.createQuery("SELECT a FROM User a WHERE a.username=:username");
			query.setParameter("username", entity.getUsername());
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		
		String userExist = null;
		if(user == null){
			em.persist(entity);
			userExist = "Account Is Created";
		}else{
			userExist = "Account Already Exist";

		}	
		return userExist;	
	}

	@Override
	public void removeUser(User entity) {
		boolean result = false;
		User user = null;
	
		try {
			user = em.find(User.class, entity.getUsername());
			result = true;
			
		} catch (NoResultException e) {
			result = false;
		}
		
		if(result = true && user != null){
			em.remove(entity);
		}
	}

	@Override
	public void updateUser(User entity) {
		User user = null;

		try {
			user = em.find(User.class, entity.getUsername());
			if(user != null){
				em.merge(entity);
			}
		} catch (NoResultException e) {
			System.out.println("User was not updated");
		}
	}

	
	@Override
	public User findUserAccount(String username) {
		User user = null;
		try {
			
			user = em.find(User.class, username);
		} catch (NoResultException e) {
			user = null;
		}	
	
		return user;
	}
	
	
	@Override
	public User findUserAccountUsernameAndPassword(String username, String password) {
		
		User user = null;
	
		try {
			
			Query query = em.createNativeQuery("select * from user where username = :username and password = :password", User.class);
			query.setParameter("username", username.trim());
			query.setParameter("password", password.trim());
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("user dao threw an except");
			user = null;
		}
	
		return user;
	}

	@Override
	public boolean doesUserNameExist(String username) {
		boolean result = false;
		
		try {
			User user =  em.find(User.class, username);			
			result = true;
			
		} catch (NoResultException e) {
			result = false;
		}		
		
		return result;
	}

}
