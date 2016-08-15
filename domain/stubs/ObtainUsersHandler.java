package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IObtainUsersHandler;

/**
 * A stub implementation of the ObtainUsersHandler
 * 
 * @author inunes, mguimas
 *
 */
public class ObtainUsersHandler extends ObtainPwdStrengthVerifiersHandler
                                implements IObtainUsersHandler {

	@Override
	public Iterable<String> getUsers() {
		System.out.println("ObtainUsersHandler: getUsers()");
		return Arrays.asList("Mary", "Peter", "John Snow");
	}

}
