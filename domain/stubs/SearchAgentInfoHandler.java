package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ISearchAgentInfoHandler;

/**
 * A stub implementation of the SearchAgentHandler
 * 
 * @author inunes, mguimas
 *
 */
public class SearchAgentInfoHandler implements ISearchAgentInfoHandler {
	
	@Override
	public boolean initiateSearch(String codeName, String accessK) {
		System.out.println("SearchAgentInfoHandler: initiateSearch(\"" 
                + codeName  + ", " + accessK + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public Iterable<String> getSpokenLanguages() {
		System.out.println("SearchAgentInfoHandler: getSpokenLanguages()");
		return Arrays.asList("1st Language", "2nd Language", "etc");
	}

	@Override
	public 	String getCodifierName() {
		System.out.println("SearchAgentInfoHandler: getCodifierName()");
		return "the agent's codifier name";	
	}
	
	@Override
	public void cancel() {
		System.out.println("SearchAgentInfoHandler: cancel()");
	}

}
