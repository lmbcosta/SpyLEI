package domain;

/**
 * This enumerate represents
 * the different types of documents
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public enum DocNature {
	TO_AGENT, FROM_AGENT, INTERNAL;
	
	/**
	 * @return String that represents
	 * 	       each value of this enumerate
	 */
	public String getWord () {
		switch(this) {
			case TO_AGENT : return "ToAg";
			
			case FROM_AGENT : return "FrAg";
			
			case INTERNAL : return "Intr";
			
			default : throw new IllegalArgumentException();
		}
	}
}

