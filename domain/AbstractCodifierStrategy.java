package domain;

/**
 * Abstract class that represents a Codifier Strategy
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public abstract class AbstractCodifierStrategy implements ICodifierStrategy {
	// Name of the codifier strategy
	private String name;
	
	/**
	 * Create and initialize an AbstractCodifierStrategy
	 * @param name The name of the codifier strategy
	 */
	public AbstractCodifierStrategy (String name) {
		this.name = name;
	}
	
	/**
	 * @return The name of this AbstractCodifierStrategy
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Code the text
	 * @param key Key used to code text 
	 * @param text text to be coded
	 * @return Coded text using key
	 * @requires key != null
	 */
	public abstract Iterable<String> code (String key, Iterable<String> text);
	
	/**
	 * Decode the text
	 * @param key Key used to decode text
	 * @param text Text to be decoded
	 * @return decoded text using key
	 * @requires key != null
	 */
	public abstract Iterable<String> decode (String key, Iterable<String> text);
}