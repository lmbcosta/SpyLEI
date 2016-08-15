package domain.interfaces;

/**
 * The interface for the Initial Object
 * 
 * @author inunes
 *
 */
public interface IOrganization {
	/**
	 * @return The handler of the login use case
	 */
	ILoginHandler getLoginHandler();
    
	/**
	 * @return The handler of the logout use case
	 */
	ILogoutHandler getLogoutHandler();
	
	/**
	 * @return The handler of the CreateAgent use case
	 */
	ICreateAgentHandler getCreateAgentHandler();
    
	/**
	 * @return The handler of the AddDocForAgent use case
	 */
	IAddDocForAgentHandler getAddDocForAgentHandler();
    
	/**
	 * @return The handler of the ReadDocAgent use case
	 */
	IReadDocAgentHandler getReadDocAgentHandler();
    
	/**
	 * @return The handler of the SetUnavailability use case
	 */
	ISetUnavailabilityHandler getSetUnavailabilityHandler();
    
	/**
	 * @return The handler of the CreateMission use case
	 */
	ICreateMissionHandler getCreateMissionHandler();
    
	/**
	 * @return The handler of the SearchAgentInfo use case
	 */
	ISearchAgentInfoHandler getSearchAgentInfoHandler();
    
	/**
	 * @return The handler of the SearchMissionInfo use case
	 */
	ISearchMissionInfoHandler getSearchMissionInfoHandler();

}
