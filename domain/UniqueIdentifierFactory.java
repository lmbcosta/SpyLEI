package domain;

/**
 * This class represents
 * an unique reference factory 
 * to documents of different types
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class UniqueIdentifierFactory {
	// Array containing referenced numbers
	// each one representing a different DocNature type
	private int[] values;
	
	// The unique instance of this class
	private static UniqueIdentifierFactory INSTANCE = 
			new UniqueIdentifierFactory();
	
	/**
	 * Initialize and construct a UniqueIdenfierFactory
	 */
	private UniqueIdentifierFactory() {
		// One value for a different DocNature
		values = new int[DocNature.values().length];
	}
	
	/**
	 * @return The only instance of this class 
	 */
	public static UniqueIdentifierFactory getInstance () {
		return INSTANCE;
	}
	
	/**
	 * Create a unique reference 
	 * of String type to a Document
	 * depending of the the kind of DocNature 
	 * passed as argument 
	 * @param dn DocNature 
	 * @return A reference to a Document with the type of dn
	 */
	  public String getIdentifier(DocNature dn) {
		// Identifier
		StringBuilder sb = new StringBuilder();
		DocNature[] array = DocNature.values();
		boolean stop = false;
		
		for (int i = 0; i < array.length && !stop; i++)
			if (dn.equals(array[i])) {
				sb.append(dn.getWord()).append(values[i]);
				stop = true;
				values[i]++;
			}
		
		return sb.toString();
	}
}
