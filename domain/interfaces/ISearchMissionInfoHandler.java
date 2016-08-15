package domain.interfaces;

/**
 * The interface for the use case Search mission information
 * 
 * @author inunes
 *
 */
public interface ISearchMissionInfoHandler {

	/**
     * Identifies the mission to be searched for
     * @param codeName The mission codename
	 * @param accessK The access key to the info of the mission 
	 * @requires The authenticated user is known && codeName != null &&
     *           accessK != null
 	 * @return true if the mission exists and the user has 
 	 *           access to it; false otherwise
    */
    boolean initiateSearch(String codeName, String accessK);

	/**
	 * The name of the agent responsible for the current mission
	 * @return
	 */
	String getResponsibleAgent();

    /**
     * The agents that participate in the current mission.
     * @return
     */
	Iterable<String> getParticipatingAgents();

    /**
     * The keywords/characteristics of the current mission.
     * @return
     */
	Iterable<String> getCharacteristics();
	
	/**
	 * Abandons the use case
	 */
	void cancel();

}
