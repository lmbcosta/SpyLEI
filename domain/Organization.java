package domain;

import services.SessionManager;
import domain.handlers.AddDocForAgentHandler;
import domain.handlers.CreateAgentHandler;
import domain.handlers.CreateMissionHandler;
import domain.handlers.LoginHandler;
import domain.handlers.LogoutHandler;
import domain.handlers.ReadDocAgentHandler;
import domain.handlers.SearchAgentInfoHandler;
import domain.handlers.SearchMissionInfoHandler;
import domain.handlers.SetUnavailabilityHandler;
import domain.interfaces.IAddDocForAgentHandler;
import domain.interfaces.ICreateAgentHandler;
import domain.interfaces.ICreateMissionHandler;
import domain.interfaces.ILogoutHandler;
import domain.interfaces.IOrganization;
import domain.interfaces.IReadDocAgentHandler;
import domain.interfaces.ISearchAgentInfoHandler;
import domain.interfaces.ISearchMissionInfoHandler;
import domain.interfaces.ISetUnavailabilityHandler;

/**
 * A stub implementation of the Organization
 * 
 * @author Luis Costa 
 * @author Manuel Caldeira 
 * @author Pedro Caldeira 
 *
 */
public class Organization implements IOrganization {
	// Names of default users
	private final static String[] NAMES = {"Mary", "Peter", "John Snow"};
	// Passwords of default users
	private final static String[] PASSWORDS = {"123", "ABC", "123ABC"};
	private final static int SIZE = NAMES.length;
	// Organization catalogs
	private UserCatalog ucat;
	private AgentCatalog acat;
	private MissionCatalog mcat;
	
	public Organization () {
		// Initialize catalogs
		ucat = new UserCatalog();
		acat = new AgentCatalog();
		mcat = new MissionCatalog();
		
		// Add default users to users catalog
		for (int i = 0; i < SIZE; i++)
			ucat.addUser(NAMES[i], PASSWORDS[i]);
	}
	
	@Override
	public LoginHandler getLoginHandler() {
		return new LoginHandler(ucat);	
	}

	@Override
	public ILogoutHandler getLogoutHandler() {
		return new LogoutHandler();
		
	}
	
	@Override
	public ICreateAgentHandler getCreateAgentHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new CreateAgentHandler(user, acat, ucat);
	}

	@Override
	public IAddDocForAgentHandler getAddDocForAgentHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new AddDocForAgentHandler(user, acat);
	}

	@Override
	public IReadDocAgentHandler getReadDocAgentHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new ReadDocAgentHandler(user, acat);
	}

	@Override
	public ISetUnavailabilityHandler getSetUnavailabilityHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new SetUnavailabilityHandler(user, acat);
	}

	@Override
	public ICreateMissionHandler getCreateMissionHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new CreateMissionHandler(user, acat, mcat, ucat);
	}

	@Override
	public ISearchAgentInfoHandler getSearchAgentInfoHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		
		return new SearchAgentInfoHandler(user, acat);
	}

	@Override
	public ISearchMissionInfoHandler getSearchMissionInfoHandler() {
		// Get the name of user in current session
		User user = getAuthenticatedUser();
		return new SearchMissionInfoHandler(user, mcat);
	}
	
	/**
	 * @return The name of the current user, 
	 * 		   null if there is no one
	 */
	private User getAuthenticatedUser() {
		// Name of the current user
		String userName = 
				SessionManager.getInstance().getAuthenticatedUser();
		// Current User
		User user = ucat.getUser(userName);
		
		return user;
	}
	
	/**
	 * 
	 * @param name User's name
	 * @return String representation of an user
	 * 	       with name name, null if there is
	 * 	       no agent with name name
	 */
	public String getUserInfo (String name) {
		String result = null;
		User user = ucat.getUser(name);
		
		if (user != null)
			result = user.toString();
		
		return result;
	}
}
