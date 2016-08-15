package domain;

/**
 * This enumerate represents the different states
 * of the agent's unavailability
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public enum Unavailable implements AvailabilityState {
	ARRESTED, DESERTED, ILL, DEAD, RETIRED;
}
