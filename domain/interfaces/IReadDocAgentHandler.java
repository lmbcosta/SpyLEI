package domain.interfaces;

/**
 * The interface for the use case Read an agent's document
 * 
 * @author inunes
 *
 */
public interface IReadDocAgentHandler {
	
	/**
     * Identifies the agent
     * @param codeName The agent codename
	 * @param accessK The agent access key
     * @requires The authenticated user is known && codeName != null
     *           && accessK != null
 	 * @return true if the agent exists and the current user has access
	 *           to its info; false otherwise
    */
	boolean readDoc (String codeName, String accessK);
	
	/**
     * Selects the document to inspect
     * @param ref The document reference
     * @requires The operation readDoc succeeded && ref != null && 
     *           ref is in docsOfCurrentAgent()
 	 * @return an iterable collection of strings representing the lines
 	 *         of the chosen document, already decoded
    */
	Iterable<String> selectDoc (String ref);
	
	
	/**
     * The references of the documents of the current agent
     * @requires The operation readDoc succeeded
     * @return iterable collection of strings representing the references
 	 *         of the documents of the current agent
    */
	Iterable<String> docsOfCurrentAgent ();

	/**
	 * Abandons the use case
	 */
	void cancel();
	
}
