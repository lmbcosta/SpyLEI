package domain.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import domain.Agent;
import domain.AgentCatalog;
import domain.Unavailable;
import domain.User;
import domain.interfaces.ISetUnavailabilityHandler;

/**
 * This Class represents a handler
 * to the operation Set unavailability
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class SetUnavailabilityHandler implements ISetUnavailabilityHandler {
	private AgentCatalog acat;
	// Current Agent
	private Agent ag;
	// List of the all names 
	// of existing unavailable states
	private List<String> unavails;
	
	/**
	 * Create and initialize a SetUnavailabilityHandler
	 * @param acat 
	 * @param user 
	 */
	public SetUnavailabilityHandler (User user, AgentCatalog acat) {
		this.acat = acat;
		this.unavails = new LinkedList<>();
		
		// Unavails
		List<Unavailable> list = Arrays.asList(Unavailable.values());
		
		for (Unavailable u : list)
			unavails.add(u.toString());
	}
	
	@Override
	public String setUnavailable(String codeName, String accessK) {
		ag = acat.getAgent(codeName);
		
		if (ag != null && ag.isRightAccessKey(accessK)) {
			return ag.availability().toString();
		} else
			return null;
	}

	@Override
	public boolean selectUnavailability(String kind) {
		if (unavails.contains(kind)) {
			ag.becomeUnavailable(kind);
			return true;
		} else
			return false;	
	}

	@Override
	public Iterable<String> kindsOfUnavailability() {
		return unavails;
	}
	
	@Override
	public void cancel() {
		System.out.println("SetUnavailabilityHandler: cancel()");
	}
}
