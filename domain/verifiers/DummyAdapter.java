package domain.verifiers;

/**
 * An example adapter

 * @author Isabel Nunes
 *
 */
public class DummyAdapter extends AbstractPwdStrengthAdapter {
    // attributes to connect to the concrete adapter
    
	public DummyAdapter () {
		super("Dummy");
	}
	
	public boolean verifyPasswordStrength(String pwd) {
		// here should appear the code to interact with the concrete device
		return true;
	}

}
