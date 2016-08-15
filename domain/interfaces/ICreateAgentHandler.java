package domain.interfaces;

/**
 * The interface for the use case Create new Agent
 * 
 * @author inunes
 *
 */
public interface ICreateAgentHandler extends IObtainUsersHandler{
	/**
	 * Starts the use case
	 * 
	 * @param codeName
	 *            The new agent codename
	 * @requires The authenticated user is known && codeName != null
	 * @return true if it is possible to continue creating a new agent named
	 *         codeName (the agent does not exist); false otherwise
	 */
	boolean initiateRegister(String codeName);

	/**
	 * Registers the access key for the new agent
	 *
	 * @param access
	 *            The access key for the new agent
	 * @param verifier
	 *            The password strength verifier to be used to measure the
	 *            quality of the access key
	 * @requires The operation initiateRegister succeeded && access != null
	 *           && verifier != null
	 * 
	 * @return true if the access key is accepted
	 */
	boolean defineAccessKey(String access, String verifier);

	/**
	 * Registers a language that the new agent speaks
	 *
	 * @param language
	 *            The name of the language
	 * @requires The operation initiateRegister and defineAccessKey succeeded
	 */
	void selectLanguage(String language);

	/**
	 * Indicates the coding key and the codifier used to code/decode the
	 * documents of the new agent
	 * 
	 * @param key
	 *            The coding key to be used in coding/decoding documents from
	 *            and to the new agent
	 * @param codifier
	 *            The name of the codifier used to code/decode the documents of
	 *            the new agent
	 * @requires The operations initiateRegister and defineAccessKey succeeded
	 * @ensures If codifier is not a valid codifier, the default codifier
	 *          Identity will be assigned to the new agent
	 */
	void defineKeyAndCoding(String key, String codifier);

	/**
	 * Selects a user that can access the info of the new agent
	 * 
	 * @param userName
	 *            The name of the authorized user
	 * @requires The operation initiateRegister and defineAccessKey succeeded
	 * @return true if the user exists in the system; false otherwise
	 */
	boolean selectUser(String userName);

	/**
	 * Confirms the creation of the new agent
	 * 
	 * @requires The operations initiateRegister and defineAccessKey succeeded
	 */
	void confirmAgentCreation();

	/**
	 * Abandons the use case
	 */
	void cancel();

	/**
	 * Which document codifiers exist available?
	 * 
	 * @return A list of names of codifiers
	 */
	Iterable<String> getDocumentCodifiers();

	/**
	 * Which languages exist available?
	 * 
	 * @return A list of names of languages
	 */
	Iterable<String> getLanguages();
}