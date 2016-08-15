package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ICreateAgentHandler;

/**
 * A stub implementation of the CreateAgentHandler
 * 
 * @author inunes, mguimas
 *
 */
public class CreateAgentHandler extends ObtainUsersHandler
                                implements ICreateAgentHandler {

	@Override
	public boolean initiateRegister(String codeName) {
		System.out.println("CreateAgentHandler: initiateRegister(\"" + codeName + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public boolean defineAccessKey(String access, String verifier) {
		System.out.println("CreateAgentHandler: defineAccessKey(\"" + access + "\", \""
	                       + verifier + "\")");
		return !access.equals("fail");
	}

	@Override
	public void selectLanguage(String language) {
		System.out.println("CreateAgentHandler: selectLanguage(\"" + language + "\")");
	}

	@Override
	public void defineKeyAndCoding(String key, String codifier) {
		System.out.println("CreateAgentHandler: defineKeyAndCoding(\"" + key + "\", \"" 
	                        + codifier + "\")");
	}

	@Override
	public boolean selectUser(String userName) {
		System.out.println("CreateAgentHandler: selectUser(\"" + userName + "\")");
		return !userName.equals("fail");
	}

	@Override
	public void confirmAgentCreation() {
		System.out.println("CreateAgentHandler: confirmAgentCreation()");
	}

	@Override
	public void cancel() {
		System.out.println("CreateAgentHandler: cancel()");
	}

	@Override
	public Iterable<String> getDocumentCodifiers() {
		System.out.println("CreateAgentHandler: getDocumentCodifiers()");
		return Arrays.asList("Identity", "Round13", "Vigenere");
	}

	@Override
	public Iterable<String> getLanguages() {
		System.out.println("CreateAgentHandler: getLanguages()");
		return Arrays.asList("Portuguese", "English", "French", "Spanish",
				"German");
	}

}
