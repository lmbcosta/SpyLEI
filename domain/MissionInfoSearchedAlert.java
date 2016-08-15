package domain;

/**
 * This class represents an alert
 * when a mission's information is required
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class MissionInfoSearchedAlert implements AlertEvent {
	private String message;
	
	public MissionInfoSearchedAlert (String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
