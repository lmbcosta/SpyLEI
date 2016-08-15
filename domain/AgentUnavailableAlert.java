package domain;

/**
 * This class represents an alert
 * when an agent become unavailable
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class AgentUnavailableAlert implements AlertEvent{
	private String message;
	
	/**
	 * Create and initialize
	 * an AgentUnavailableAlert
	 * @param message Message to be
	 * 		  displayed on the alert
	 */
	public AgentUnavailableAlert (String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
		
	}

}
