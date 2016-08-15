package domain.handlers;

import domain.Agent;
import domain.AgentCatalog;
import domain.User;
import domain.interfaces.IReadDocAgentHandler;

/**
 * This Class represents a handler
 * to the operation Read document Agent
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class ReadDocAgentHandler implements IReadDocAgentHandler {
	private AgentCatalog acat;
	// Current Agent
	private Agent ag;
	
	/**
	 * Create and initialize a ReadDocAgentHandler
	 * @param acat
	 * @param user
	 * @requires acat != null && user != null
	 */
	public ReadDocAgentHandler (User user, AgentCatalog acat) {
		this.acat = acat;
	}
	
	@Override
	public boolean readDoc(String codeName, String accessK) {
		ag = acat.getAgent(codeName);
		
		if (ag != null)
			return ag.isRightAccessKey(accessK);
		else
			return false;
	}

	@Override
	public Iterable<String> selectDoc(String ref) {
		return ag.decodifiedDocText(ref);
	}

	@Override
	public Iterable<String> docsOfCurrentAgent() {
		return ag.documentReference();
		
	}
	
	@Override
	public void cancel() {
		System.out.println("ReadDocAgentHandler: cancel()");
	}

}
