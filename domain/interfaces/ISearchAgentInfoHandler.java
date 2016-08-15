package domain.interfaces;

/**
 * The interface for the use case Search Agent information
 * 
 * @author inunes
 *
 */
public interface ISearchAgentInfoHandler {

	/**
     * Identifies the agent to be searched for
     * @param codeName The agent codename
	 * @param accessK The access key to the info of the agent 
	 * @requires The authenticated user is known && codeName != null &&
     *           accessK != null
 	 * @return true if the agent exists and the user has 
 	 *           access to it; false otherwise
    */
    boolean initiateSearch(String codeName, String accessK);

    /**
     * The languages spoken by the current agent.
     * @return
     */
	Iterable<String> getSpokenLanguages();
	
	/**
	 * The name of the codifier of the current agent
	 * @return
	 */
	String getCodifierName();
	
	/**
	 * Abandons the use case
	 */
	void cancel();

}
