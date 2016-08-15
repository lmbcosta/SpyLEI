package domain.handlers;

import java.util.LinkedList;
import java.util.List;

import domain.interfaces.IObtainPwdStrengthVerifiersHandler;
import domain.verifiers.VerifierFactory;

/**
 * A stub implementation of the ObtainPwdStrengthVerifiersHandler
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class ObtainPwdStrengthVerifiersHandler implements IObtainPwdStrengthVerifiersHandler {

	@Override
	public List<String> getPwdStrengthVerifiers() {
		Iterable<String> it = VerifierFactory.INSTANCE.pwdStrengthAdaptersList();
		List<String> list = new LinkedList<>();
		
		for (String s : it)
			list.add(s);
		
		return list;
	}
}
