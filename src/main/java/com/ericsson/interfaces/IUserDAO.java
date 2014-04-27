
package com.ericsson.interfaces;



import com.ericsson.entities.User;




public interface IUserDAO {

	public String addUser(User entity);
	
	public void removeUser(User entity);
	
	public void updateUser(User entity);
	
	public User findUserAccount(String username);
	
	boolean doesUserNameExist(String username);

	public User findUserAccountUsernameAndPassword(String username, String password);

	
}
