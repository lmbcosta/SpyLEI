package domain.handlers;

import domain.interfaces.ILogoutHandler;
import services.SessionManager;

/**
 * A stub implementation of the LogoutHandler
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class LogoutHandler implements ILogoutHandler {

	@Override
	public void logout() {
		SessionManager.getInstance().deleteSession();
	}
}
