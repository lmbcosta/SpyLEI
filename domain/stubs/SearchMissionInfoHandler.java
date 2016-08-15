package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ISearchMissionInfoHandler;

/**
 * A stub implementation of the SearchMissionHandler
 * 
 * @author inunes, mguimas
 *
 */
public class SearchMissionInfoHandler implements ISearchMissionInfoHandler {

	@Override
	public boolean initiateSearch(String codeName, String accessK) {
		System.out.println("SearchMissionInfoHandler: initiateSearch(\"" 
                + codeName + ", " + accessK + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public String getResponsibleAgent() {
		System.out.println("SearchMissionInfoHandler: getResponsibleAgent()");
		return "Responsible agent";
	}

	@Override
	public Iterable<String> getParticipatingAgents() {
		System.out.println("SearchMissionInfoHandler: getParticipatingAgents()");
		return Arrays.asList("One agent", "another agent");
	}

	@Override
	public Iterable<String> getCharacteristics() {
		System.out.println("SearchMissionInfoHandler: getCharacteristics()");
		return Arrays.asList("1st keyword", "2nd keyword", "etc");
	}

	@Override
	public void cancel() {
		System.out.println("SearchMissionInfoHandler: cancel()");

	}

}
