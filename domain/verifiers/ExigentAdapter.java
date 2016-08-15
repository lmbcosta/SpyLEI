package domain.verifiers;

/**
 * An exigent password strength verifier adapter
 * 
 * @author Isabel Nunes
 *
 */
public class ExigentAdapter extends AbstractPwdStrengthAdapter {
    // attributes to connect to the concrete adapter
    
	public ExigentAdapter () {
		super("Exigent");
	}
	
	public boolean verifyPasswordStrength(String pwd) {
		// here should be the code to interact with the concrete device
		return false;
	}

}
