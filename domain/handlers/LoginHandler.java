package domain.handlers;

import domain.User;
import domain.UserCatalog;
import domain.interfaces.ILoginHandler;
import services.SessionManager;

/**
 * This Class represents a handler
 * to the operation User Login
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class LoginHandler extends ObtainUsersHandler implements ILoginHandler {
	// Session Manager
	private SessionManager sm;
	
	/**
	 * Create and initialize a LoginHandler
	 * @param ucat User catalog
	 * @requires ucat != null
	 */
	public LoginHandler(UserCatalog ucat) {
		super(ucat);
		sm = SessionManager.getInstance();
	}
	
	@Override
	public boolean login(String username, String password) {
		User user = ucat.getUser(username);
		if (user != null && user.isRightPassword(password)) {
			sm.createSession(username);
			return true;
		} else
			return false;	
	}
}
