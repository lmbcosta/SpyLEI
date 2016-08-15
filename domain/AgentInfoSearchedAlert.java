package domain;

/**
 * This class represents an alert
 * when an agent's information is required
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class AgentInfoSearchedAlert implements AlertEvent {
	private String message;
	
	/**
	 * Create and initialize
	 * an AgentUnavailableAlert
	 * @param message Message to be
	 * 		  displayed on the alert
	 */
	public AgentInfoSearchedAlert (String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
