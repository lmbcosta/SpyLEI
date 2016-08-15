package domain;

import java.util.Map;
import java.util.HashMap;

/**
 * This class represents an agents catalog
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class AgentCatalog {
	private Map<String, Agent> agents;
	
	/**
	 * Create and initialize an AgentCatalog
	 */
	public AgentCatalog() {
		agents = new HashMap<>();
	}
	
	/**
	 * @param name Name of the agent to return
	 * @return Agent with the name name, 
	 * @requires name != null && existsAgent(name)
	 */
	public Agent getAgent (String name) {
		 return agents.get(name);
	}
	
	/**
	 * Check if exists an agent with name name
	 * @param name Agent's name
	 * @return True if exists an Agent with name name,
	 * 		   false, otherwise
	 * @requires name != null
	 */
	public boolean existsAgent(String name) {
		return agents.containsKey(name);
	}
	
	/**
	 * Add a new agent to this AgentCatalog
	 * @param ag Agent to add
	 * @requires ag != null
	 */
	public void newAgent (Agent ag) {
		agents.put(ag.getName(), ag);
	}	
}
