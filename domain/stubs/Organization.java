package domain.stubs;

import domain.interfaces.IAddDocForAgentHandler;
import domain.interfaces.ICreateAgentHandler;
import domain.interfaces.ICreateMissionHandler;
import domain.interfaces.ILogoutHandler;
import domain.interfaces.IOrganization;
import domain.interfaces.IReadDocAgentHandler;
import domain.interfaces.ISearchAgentInfoHandler;
import domain.interfaces.ISearchMissionInfoHandler;
import domain.interfaces.ISetUnavailabilityHandler;

/**
 * A stub implementation of the Organization
 * 
 * @author inunes, mguimas
 *
 */
public class Organization implements IOrganization {

	@Override
	public LoginHandler getLoginHandler() {
		return new LoginHandler();
	}

	@Override
	public ILogoutHandler getLogoutHandler() {
		return new LogoutHandler();
	}
	
	@Override
	public ICreateAgentHandler getCreateAgentHandler() {
		return new CreateAgentHandler();
	}

	@Override
	public IAddDocForAgentHandler getAddDocForAgentHandler() {
		return new AddDocForAgentHandler();
	}

	@Override
	public IReadDocAgentHandler getReadDocAgentHandler() {
		return new ReadDocAgentHandler();
	}

	@Override
	public ISetUnavailabilityHandler getSetUnavailabilityHandler() {
		return new SetUnavailabilityHandler();
	}

	@Override
	public ICreateMissionHandler getCreateMissionHandler() {
		return new CreateMissionHandler();
	}

	@Override
	public ISearchAgentInfoHandler getSearchAgentInfoHandler() {
		return new SearchAgentInfoHandler();
	}

	@Override
	public ISearchMissionInfoHandler getSearchMissionInfoHandler() {
		return new SearchMissionInfoHandler();
	}

}
