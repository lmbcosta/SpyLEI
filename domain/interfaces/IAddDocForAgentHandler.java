package domain.interfaces;

/**
 * The interface for the use case Add document for agent
 * 
 * @author inunes
 *
 */
public interface IAddDocForAgentHandler {

	/**
     * Identifies the agent for which to add a document
     * @param codeName The agent codename
	 * @param accessK The agent access key
     * @requires The authenticated user is known && codeName != null
     *           && accessK != null
 	 * @return true if the agent with codeName exists and the current
 	 *           user has access to its info; false otherwise
    */
    boolean giveAgentName(String codeName, String accessK);
    
	/**
     * Gives the name of the file
     * @param fileName The file name
     * @requires name != null && the operation giveAgentName succeeded
 	 * @return unique identifier for the new document if the file with fileName 
 	 *          is readable; null otherwise
 	 * @ensures if the file is readable, a new document is created from the 
 	 *           file's text after codifying it, and associated to the current
 	 *           agent
    */
    String giveDocumentName(String fileName);

	/**
	 * Abandons the use case
	 */
	void cancel();
   
}
