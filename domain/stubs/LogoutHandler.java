package domain.stubs;

import domain.interfaces.ILogoutHandler;
import services.SessionManager;

/**
 * A stub implementation of the LogoutHandler
 * 
 * @author inunes, mguimas
 *
 */
public class LogoutHandler implements ILogoutHandler {

	@Override
	public void logout() {
		System.out.println("LogoutHandler: logout()");
		SessionManager.getInstance().deleteSession();
	}
}
