package domain.stubs;

import domain.interfaces.ILoginHandler;
import services.SessionManager;

/**
 * A stub implementation of the LoginHandler
 * 
 * @author inunes, mguimas
 *
 */

public class LoginHandler extends ObtainUsersHandler implements ILoginHandler {

	@Override
	public boolean login(String username, String password) {
		System.out.println("LoginHandler: login(\"" + username + "\", \"" 
	                        + password + "\")");

		boolean success = !username.equals("fail");
		
		if (success)
			SessionManager.getInstance().createSession(username);
		return success;
	}

}
