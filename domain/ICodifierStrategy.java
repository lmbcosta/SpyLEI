package domain;

/**
 * This interface defines 
 * coding strategies to documents 
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public interface ICodifierStrategy {
	/**
	 * @return Name of the coding strategy
	 */
	String getName ();
	
	/**
	 * Code the text
	 * @param key Key used to code text 
	 * @param text Message to be coded
	 * @return Text coded using key
	 */
	Iterable<String> code (String key, Iterable<String> text);
	
	/**
	 * Decode the text
	 * @param key Key used to decode text
	 * @param text Message to be decoded
	 * @return Text decoded using key
	 */
	Iterable<String> decode (String key, Iterable<String> text);
}
