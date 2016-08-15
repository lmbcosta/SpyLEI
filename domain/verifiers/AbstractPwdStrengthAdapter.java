package domain.verifiers;

/**
 * An abstract password strength verifier adapter implementation
 * Notice that the class does not implement the verifyPasswordStrength
 * method. It remains abstract and will be implemented by concrete
 * sub-classes of this class.
 * 
 * @author Isabel Nunes
 *
 */
public abstract class AbstractPwdStrengthAdapter implements IPwdStrengthAdapter {
    
	/**
	 * The name of the verifier 
	 */
	private String name;
    
	/**
	 * Constructs a verifier adapter given its name
	 * 
	 * @param name The name of the verifier adapter
	 */
	public AbstractPwdStrengthAdapter (String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
