package domain;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents an users catalog
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class UserCatalog {
	private Map<String, User> users;
	
	/**
	 * Create and initialize an UserCatalog
	 */
	public UserCatalog () {
		users = new HashMap<>();
	}
	
	/**
	 * Add a new user to this catalog
	 * @param name User's name
	 * @param pwd User's password
	 * @requires name != null && pwd != null
	 */
	public void addUser (String name, String pwd) {
		User user = new User(name, pwd);
		users.put(user.getName(), user);
	}
	
	/**
	 * @param name User's name
	 * @return User with name name,
	 * 		   null if there is no agent
	 * 		   with name name
	 * @requires existsAgent(name)
	 */
	public User getUser (String name) {
		return users.get(name);
	}
	
	/**
	 * Check if exists an user with name name
	 * @param name User's name
	 * @return True if exists an user with name name,
	 * 		   false, otherwise
	 * @requires name != null
	 */
	public boolean existsUser (String name) {
		return users.containsKey(name);
	}
	
	/**
	 * Add an agent to an User
	 * if his name is in List users
	 * @param users List of names of users to add an Agent ag
	 * @param ag Agent to add
	 * @requires ag != null && users != null
	 */
	public void addAgentToUsers (List<String> users, Agent ag) {
		User user;
		// Loop users to add an agent
		for (String s: users) {
			user = this.users.get(s);
			if (user != null)
				user.addAgent(ag);
		}		
	}
	
	/**
	 * Add a mission to an User
	 * if his name is in List users
	 * @param users List of names of users to add a Mission mi
	 * @param mi Mission to add
	 * @requires mi != null && users != null
	 */
	public void addMissionToUsers (List<String> users, Mission mi) {
		User user;
		// Loop users to add a mission
		for (String s : users) {
			user = this.users.get(s);
			if (user != null)
				user.addMission(mi);
		}	
	}
	
	/**
	 * @return Iterable containning the names
	 * 	       of all users in this User catalog
	 */
	public Iterable<String> getUsers() {
		return users.keySet();
	}
	
	
	
}
