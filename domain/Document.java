package domain;

import java.util.List;

/**
 * This class represents a Document
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class Document implements Cloneable {
	// Reference
	private String ref;
	// Text
	private List<String> text;
	
	/**
	 * Create and initialize an Document
	 * @param ref Referente to the document
	 * @param text Document text
	 */
	public Document (String ref, List<String> text) {
		this.ref = ref;
		this.text = text;
	}
	
	/**
	 * @return The reference of this document
	 */
	public String getReference () {
		return ref;
	}
	
	/**
	 * @return The text of this document
	 */
	public Iterable<String> getText () {
		return text;
	}
	
	@Override
	public Object clone() {
		Document doc = null;
		
		try {
			doc = (Document) super.clone();
			doc.ref = this.ref;
			for (String s : this.text)
				doc.text.add(new String(s));
			
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
		return doc;
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Reference: ").append(ref).append("\n");
		sb.append("CodifiedText:");
		for (String s : text)
			sb.append("\n").append(s);
		
		return sb.toString();
	}
}
