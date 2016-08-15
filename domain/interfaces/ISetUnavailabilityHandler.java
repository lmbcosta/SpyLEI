package domain.interfaces;

/**
 * The interface for the use case Register an agent's unavailability
 * 
 * @author inunes
 *
 */
public interface ISetUnavailabilityHandler {
	
	/**
     * Identifies the agent to be made unavailable
     * @param codeName The agent codename
     * @param accessK The agent access key
     * @requires The authenticated user is known && codeName != null &&
     *           accessK != null
 	 * @return the availability of the agent with codeName if that agent exists,
 	 *         its access key is accessK and the current user has access to its 
 	 *         info; null otherwise
    */
	String setUnavailable (String codeName, String accessK);
	
	/**
     * Defines the kind of unavailability
     * @param kind The kind of unavailability
     * @requires The operation setUnavailable succeeded && kind != null
	 * @return true if all went well
    */
	boolean selectUnavailability (String kind);
	
	/**
     * The acceptable kinds of unavailability
     * @requires The operation setUnavailable succeeded
     * @return a collection of strings representing the acceptable kinds of 
 	 *         unavailability for an agent
    */
	Iterable<String> kindsOfUnavailability ();

	/**
	 * Abandons the use case
	 */
	void cancel();

}
