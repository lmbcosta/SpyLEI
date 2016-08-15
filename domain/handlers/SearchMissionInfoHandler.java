package domain.handlers;

import domain.Mission;
import domain.MissionCatalog;
import domain.User;
import domain.interfaces.ISearchMissionInfoHandler;

/**
 * This Class represents a handler
 * to the operation search mission Info
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class SearchMissionInfoHandler implements ISearchMissionInfoHandler {
	private MissionCatalog mcat;
	private Mission mi;
	
	/**
	 * Create and initialize a SearchMissionInfoHandler
	 * @param user Current User
	 * @param mcat Mission catalog
	 * @requires user != null && mcat != null
	 */
	public SearchMissionInfoHandler (User user, MissionCatalog mcat) {
		this.mcat = mcat;
	}
	
	@Override
	public boolean initiateSearch(String codeName, String accessK) {
		mi = mcat.getMission(codeName);
		
		if (mi != null) 
			return mi.isRightAccessKey(accessK);
		else
			return false;
	}

	@Override
	public String getResponsibleAgent() {
		return mi.getResponsible();
	}

	@Override
	public Iterable<String> getParticipatingAgents() {
		return mi.getParticipants();
	}

	@Override
	public Iterable<String> getCharacteristics() {
		return mi.getCharacteristics();
	}

	@Override
	public void cancel() {
		System.out.println("SearchMissionInfoHandler: cancel()");
	}

}
