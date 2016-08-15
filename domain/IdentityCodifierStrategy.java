package domain;


/**
 * This class represents a codifier strategy
 * to code/decode text
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class IdentityCodifierStrategy extends AbstractCodifierStrategy {
	// Name of the codifier strategy
	private static final String NAME = "Identity";
	
	/**
	 * Create and initializes an IdentityCodifierStrategy
	 */
	public IdentityCodifierStrategy () {
		super(NAME);
	}
	
	@Override
	public Iterable<String> code (String key, Iterable<String> text) {
		return  text;
	}
	
	@Override
	public Iterable<String> decode (String key, Iterable<String> text) {
		return text;
	}	
}
