package domain.handlers;

import java.util.LinkedList;
import java.util.List;

import domain.Agent;
import domain.AgentCatalog;
import domain.Mission;
import domain.MissionCatalog;
import domain.User;
import domain.UserCatalog;
import domain.interfaces.ICreateMissionHandler;
import domain.verifiers.IPwdStrengthAdapter;
import domain.verifiers.VerifierFactory;

/**
 * This Class represents a handler
 * to the operation Create mission
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class CreateMissionHandler extends ObtainUsersHandler
                                  implements ICreateMissionHandler {
	
	private AgentCatalog acat;
	private MissionCatalog mcat;
	// Current user
	private User user;
	// Current mission
	private Mission mi;
	// Name of the users that access mission
	private List<String> users;
	
	/**
	 * Create and initialize a CreateMissionHandler
	 * @param user Current user
	 * @param acat Agent catalog
	 * @param mcat Mission catalog
	 * @param ucat User catalog
	 * @requires user != null && acat && null
	 * 		     mcat != null && ucat != null
	 */
	public CreateMissionHandler (User user, AgentCatalog acat, 
			MissionCatalog mcat, UserCatalog ucat) {
		super(ucat);
		this.acat = acat;
		this.mcat = mcat;
		this.user = user;
		this.users = new LinkedList<>();
	}
	
	@Override
	public boolean initiateRegister(String codeName) {
		if (!mcat.existsMission(codeName)) {
			mi = new Mission(codeName);
			return true;
		} else
			return false;
	}

	@Override
	public boolean defineAccessKey(String access, String verifier) {
		VerifierFactory vf = VerifierFactory.INSTANCE;
		IPwdStrengthAdapter adapter = vf.getPwdStrengthAdapter(verifier);
		
		if (adapter.verifyPasswordStrength(access)) {
			mi.setAccessKey(access);
			return true;
		} else
			return false;
	}

	@Override
	public boolean selectResponsible(String codeName, String accessK) {
		Agent ag = acat.getAgent(codeName);
		if (ag != null && ag.isRightAccessKey(accessK) &&
				user.hasAgent(ag)) {
			mi.setResponsible(ag);
			return true;
		} else
			return false;
	}

	@Override
	public boolean selectAgent(String codeName, String accessK) {
		Agent ag = acat.getAgent(codeName);
		if (ag != null && ag.isRightAccessKey(accessK) &&
				user.hasAgent(ag)) {
			mi.setParticipationAgent(ag);
			return true;
		} else
			return false;
	}

	@Override
	public void defineKeyword(String keyword) {
		mi.setCharacteristic(keyword);
	}

	@Override
	public boolean selectUser(String userName) {
		if (ucat.existsUser(userName)) {
			users.add(userName);
			return true;
		} else
			return false;
	}

	@Override
	public void confirmMissionCreation() {
		// Add current mission to catalog
		mcat.newMission(mi);
		ucat.addMissionToUsers(users, mi);
		user.addMission(mi);
	}

	@Override
	public void cancel() {
		System.out.println("CreateMissionHandler: cancel()");
	}
}
