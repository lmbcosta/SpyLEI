package domain.interfaces;

/**
 * The interface for the use case Create new Mission
 * 
 * @author inunes
 *
 */
public interface ICreateMissionHandler extends IObtainUsersHandler {

	/**
	 * Starts the use case
	 * 
	 * @param codeName
	 *            The new mission codename
	 * @requires The authenticated user is known && codeName != null
	 * @return true if it is possible to continue creating a new mission named
	 *         codeName (a mission with that name does not exist) ; 
	 *         false otherwise
	 */
	boolean initiateRegister(String codeName);

	/**
	 * Registers the access key for the new mission
	 *
	 * @param access
	 *            The access key for the new mission
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
	 * Registers the agent that will be responsible for the new mission
	 *
	 * @param codeName
	 *            The codeName of the agent
	 * @param accessK
	 *            The access key to the agent
	 * @requires The operations initiateRegister and defineAccessKey succeeded 
	 *           && codeName != null && accessK != null
	 * @return true if the agent with that name and access key exists in the
	 *         system and the authenticated user has access to it; 
	 *         false otherwise
	 */
	boolean selectResponsible(String codeName, String accessK);

	/**
	 * Registers an agent to be assigned to the new mission.
	 * 
	 * @param codeName
	 *            The codename of the agent
	 * @param accessK
	 *            The access key of the agent
	 * @requires The operations initiateRegister and defineAccessKey succeeded 
	 *            && codeName != null && accessK != null
	 * @return true if the agent with that name and access key exists in the
	 *         system and the authenticated user has access to it; 
	 *         false otherwise
	 */
	boolean selectAgent(String codeName, String accessK);

	/**
	 * Registers a keyword that characterizes the new mission
	 *
	 * @param keyword
	 *            A keyword characterizing the new mission
	 * @requires The operations initiateRegister and defineAccessKey succeeded 
	 *           && keyword != null
	 */
	void defineKeyword(String keyword);

	/**
	 * Selects a user that can access the info of the new mission
	 * 
	 * @param userName
	 *            The name of the authorized user
	 * @requires The operations initiateRegister and defineAccessKey succeeded 
	 *           && userName != null
	 * @return true if the user exists in the system; false otherwise
	 */
	boolean selectUser(String userName);

	/**
	 * Confirms the creation of the new mission
	 * 
	 * @requires The operations initiateRegister and defineAccessKey succeeded
	 */
	void confirmMissionCreation();

	/**
	 * Abandons the use case
	 */
	void cancel();

}
