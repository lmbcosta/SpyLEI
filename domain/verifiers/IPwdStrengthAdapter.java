package domain.verifiers;

/**
 * A password strength verifier adapter
 * 
 * @author Isabel Nunes
 *
 */
public interface IPwdStrengthAdapter {
	
	/**
	 * @return The verifier name
	 */
	String getName();
	
    /**
     * @param pwd The password to verify whether it is strong or not
     * @return True is pwd is considered strong; false otherwise
     */
    boolean verifyPasswordStrength(String pwd);
}
