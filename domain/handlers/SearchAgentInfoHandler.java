package domain.handlers;

import domain.Agent;
import domain.AgentCatalog;
import domain.User;
import domain.interfaces.ISearchAgentInfoHandler;

/**
 * This Class represents a handler
 * to the operation Search agent info
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class SearchAgentInfoHandler implements ISearchAgentInfoHandler {
	// User catalog
	private AgentCatalog acat;
	// Current Agent
	private Agent ag;
	
	/**
	 * Create and initialize a SearchAgentInfoHandler
	 * @param acat Agent catalog
	 * @param user Current user
	 * @requires acat != null && user != null
	 */
	public SearchAgentInfoHandler (User user, AgentCatalog acat) {
		this.acat = acat;
	}
	
	@Override
	public boolean initiateSearch(String codeName, String accessK) {
		ag = acat.getAgent(codeName);
		
		if (ag != null) {
			return ag.isRightAccessKey(accessK);
		} else
			return false;
	}

	@Override
	public Iterable<String> getSpokenLanguages() {
		return ag.spokenLanguages();
	}

	@Override
	public 	String getCodifierName() {
		return ag.codifierName();
	}
	
	@Override
	public void cancel() {
		System.out.println("SearchAgentInfoHandler: cancel()");
	}
}
