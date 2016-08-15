package domain.handlers;

import domain.UserCatalog;
import domain.interfaces.IObtainUsersHandler;
import domain.stubs.ObtainPwdStrengthVerifiersHandler;


public class ObtainUsersHandler extends ObtainPwdStrengthVerifiersHandler
                                implements IObtainUsersHandler {
	
	// User catalog attribute
	protected UserCatalog ucat;
	
	/**
	 * Create and initialize an ObtainUsersHandler
	 * @param uc Users catalog
	 * @requires uc != null
	 */
	public ObtainUsersHandler (UserCatalog uc) {
		this.ucat = uc;
	}
	
	@Override
	public Iterable<String> getUsers() {
		return ucat.getUsers();
	}

}
