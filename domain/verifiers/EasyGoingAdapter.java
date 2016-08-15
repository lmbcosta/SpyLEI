package domain.verifiers;

/**
 * A pedometer adatper
 * 
 * @author Isabel Nunes
 *
 */
public class EasyGoingAdapter extends AbstractPwdStrengthAdapter {
    // attributes to connect to the concrete adapter
    
	public EasyGoingAdapter () {
		super("EasyGoing");
	}
	
	public boolean verifyPasswordStrength(String pwd) {
		// here should appear the code to interact with the concrete device
		return true;
	}
}
