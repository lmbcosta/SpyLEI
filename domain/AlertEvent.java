package domain;

/**
 * This Interface defines the type
 * of events that want to be observed
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public interface AlertEvent {
	
	/**
	 * @return Message associated
	 * 		   to a type of event
	 */
	public String getMessage();
}
