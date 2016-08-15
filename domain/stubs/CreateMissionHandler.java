package domain.stubs;

import domain.interfaces.ICreateMissionHandler;

/**
 * A stub implementation of the CreateMissionHandler
 * 
 * @author inunes, mguimas
 *
 */
public class CreateMissionHandler extends ObtainUsersHandler
                                  implements ICreateMissionHandler {

	@Override
	public boolean initiateRegister(String codeName) {
		System.out.println("CreateMissionHandler: initiateRegister(\"" 
                + codeName + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public boolean defineAccessKey(String access, String verifier) {
		System.out.println("CreateMissionHandler: defineAccessKey(\"" + access + "\", \"" + verifier + "\")");
		return !access.equals("fail");
	}

	@Override
	public boolean selectResponsible(String codeName, String accessK) {
		System.out.println("CreateMissionHandler: selectResponsible(\"" 
                + codeName + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public boolean selectAgent(String codeName, String accessK) {
		System.out.println("CreateMissionHandler: selectAgent(\"" 
                + codeName + "\")");
		return !codeName.equals("fail");
	}

	@Override
	public void defineKeyword(String keyword) {
		System.out.println("CreateMissionHandler: defineKeyword(\"" +
                keyword + "\")");
	}

	@Override
	public boolean selectUser(String userName) {
		System.out.println("CreateMissionHandler: selectUser(\"" 
                + userName + "\")");
		return !userName.equals("fail");
	}

	@Override
	public void confirmMissionCreation() {
		System.out.println("CreateMissionHandler: confirmMissionCreation()");
	}

	@Override
	public void cancel() {
		System.out.println("CreateMissionHandler: cancel()");
	}
}
