package domain.stubs;

import java.util.Arrays;
import java.util.List;

import domain.interfaces.IObtainPwdStrengthVerifiersHandler;

/**
 * A stub implementation of the ObtainPwdStrengthVerifiersHandler
 * 
 * @author inunes, mguimas
 *
 */
public class ObtainPwdStrengthVerifiersHandler implements IObtainPwdStrengthVerifiersHandler {

	@Override
	public List<String> getPwdStrengthVerifiers() {
		//System.out.println("ObtainPwdStrengthVerifiersHandler: getPwdStrengthVerifiers()");
		return Arrays.asList("Dummy", "Exigent", "EasyGoing");
	}

}
