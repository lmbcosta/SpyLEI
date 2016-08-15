package domain.handlers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import domain.Agent;
import domain.AgentCatalog;
import domain.CodifierFactory;
import domain.User;
import domain.UserCatalog;
import domain.interfaces.ICreateAgentHandler;
import domain.verifiers.IPwdStrengthAdapter;
import domain.verifiers.VerifierFactory;

/**
 * This Class represents a handler
 * to the operation Create new agent
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class CreateAgentHandler extends ObtainUsersHandler
                                implements ICreateAgentHandler {
	
	// Atributes
	private AgentCatalog acat;
	// Current user
	private User user;
	// Current agent
	private Agent ag;
	// User's name that have access to the Agent ag
	private List<String> users;
	
	
	/**
	 * Create and initialize a CreateAgentHandler
	 * @param ucat User catalog
	 * @param acat Agent catalog
	 * @param user Current user
	 * @requires ucat != null && acat != null && user != null
	 */
	public CreateAgentHandler(User user, AgentCatalog acat, UserCatalog ucat ) {
		super(ucat);
		this.acat = acat;
		this.user = user;
		users = new LinkedList<>();
	}
	
	@Override
	public boolean initiateRegister(String codeName) {
		if (!acat.existsAgent(codeName)) {
			ag = new Agent(codeName);
			return true;
		} else
			 return false;
	}

	@Override
	public boolean defineAccessKey(String access, String verifier) {
		VerifierFactory vf = VerifierFactory.INSTANCE;
		IPwdStrengthAdapter adapter = vf.getPwdStrengthAdapter(verifier);
		
		if (adapter.verifyPasswordStrength(access)) {
			ag.defineAccessKey(access);
			return true;
		} else
			return false;
	}

	@Override
	public void selectLanguage(String language) {
		ag.addLanguage(language);
	}

	@Override
	public void defineKeyAndCoding(String key, String codifier) {
		ag.defineCodingKey(key);
		ag.defineCodingStrategy(codifier);
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
	public void confirmAgentCreation() {
		// Add Agent ag 
		acat.newAgent(ag);
		// Add Agent to users in User catalog
		ucat.addAgentToUsers(users, ag);
		// Add agent to current user
		user.addAgent(ag);
	}

	@Override
	public void cancel() {
		System.out.println("CreateAgentHandler: cancel()");
	}

	@Override
	public Iterable<String> getDocumentCodifiers() {
		return CodifierFactory.getInstance().getCodifiersNameList();
	}

	@Override
	public Iterable<String> getLanguages() {
		return Arrays.asList("Portuguese", "English", "French", "Spanish", "German");
	}
}
