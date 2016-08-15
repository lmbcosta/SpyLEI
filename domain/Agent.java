package domain;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.Observable;

/**
 * This Class represents an Agent
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class Agent extends Observable {
	// Agent's name
	private String name;
	// Access Key
	private String ack;
	// AvailabilityState
	private AvailabilityState availability;
	// List of spoken languages
	private List<String> languages;
	// CodeStrategy for this Agent
	private ICodifierStrategy codifier;
	// Coding key
	private String codingKey;
	// Received documents
	private Map<String, Document> toAgent;
	// Sent Documents
	private Map<String, Document> fromAgent;
	
	/**
	 * Create and initialize an Agent
	 * @param name Agent´s name
	 * @requires name != null
	 */
	public Agent (String name) {
		this.name = name;
        // By default
		availability = InAction.AVAILABLE;
		languages = new LinkedList<>();
		toAgent = new HashMap<>();
		fromAgent = new HashMap<>();
	}
	
	/**
	 * @return Name of this Agent
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * @return The availability of this Agent
	 */
	public AvailabilityState availability () {
		return availability;
	}
	
	/**
	 * Define an Access key to this agent
	 * @param ack Access key
	 * @requires ack != null
	 */
	public void defineAccessKey (String ack) {
		this.ack = ack;
	}
	
	/**
	 * Check if key is the correct access key
	 * @param key Key to test
	 * @requires key != null
	 * @return True if key is the access key,
	 * 		   false otherwise		
	 */
	public boolean isRightAccessKey (String key) {
		return ack.equals(key);
	}
	
	/**
	 * Add a language to this Agent
	 * @param lang Language to be added
	 * @requires lang != null
	 */
	public void addLanguage (String lang) {
		if (!languages.contains(lang))
			languages.add(lang);
	}
	
	/**
	 * Defines a code strategy to this agent
	 * @param s Reference to the codeStrategy
	 * @requires s != null
	 */
	public void defineCodingStrategy (String s) {
		// Singleton class ClassFactory
		codifier = CodifierFactory.getInstance().getCodifier(s);  
	}
	
	/**
	 * Define a coding key to this Agent
	 * @param key Coding Key
	 * @requires key != null
	 */
	public void defineCodingKey (String key) {
		this.codingKey = key;
	}
	
	/**
	 * @return Iterator of the spoken languages
	 * 	       of this agent
	 */
	public Iterable<String> spokenLanguages () {
		return languages;
	}
	
	/**
	 * @return The name of CodifyStrategy
	 * 		   used for this Agent
	 */
	public String codifierName () {
		// Notify Observers 
		String msg = "Agent " + name + " info was searched"; 
		AlertEvent alert = new AgentInfoSearchedAlert(msg);
		setChanged();
		notifyObservers(alert);
		return codifier.getName();
	}
	
	/**
	 * Create a new Document
	 * @param fr FileReader that contains the text
	 * 		  of the document
	 * @param dn Enum gives the nature of the Document:
	 * 		  to agent, from agent, internal
	 * @return Reference to the new document created
	 */
	public String createDocument (FileReader fr, DocNature dn) {
		// BufferedReader to read FileReader
		BufferedReader output = new BufferedReader(fr);
		// String to read from BufferedReader
		String s = "";
		// Identifier
		String identifier = null;
		List<String> list = new LinkedList<>();
		
		// Add strings to the list
		try {
			while ((s = output.readLine()) != null)
				list.add(s);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Get identifier to create document
		identifier = getIdentifier(dn);
		
		// Code the text 
		list = code(list);
		
		/*
		 * Create a new document 
		 * and save in the correct folder
		 */
		switch (dn) {
			case TO_AGENT : 
				toAgent.put(identifier, new Document(identifier, list));
				break;
			case FROM_AGENT :
				fromAgent.put(identifier, new Document(identifier, list));
				break;
			default: throw new IllegalArgumentException();
		}
		// Return reference to the Document
		return identifier;
	}
	
	/**
	 * Decode text
	 * @param ref Reference to the text to be decoded
	 * @return A list containing text decoded 
	 * 	       null if there isn't a document without this ref
	 */
	public Iterable<String> decodifiedDocText (String ref) {
		Document document = null;
		// Search for document with reference ref
		// Search in fromAgent
		if (fromAgent.containsKey(ref))
				document = fromAgent.get(ref);
		
		// Search in toAgent
		if (document == null) 
			if (toAgent.containsKey(ref))
				document = toAgent.get(ref);
		
		if (document == null)
			return null;
		else {
			Iterable<String> it = document.getText();
			LinkedList<String> list = new LinkedList<>();
			
			// Passing lines of text to the list
			for (String s : it)
				list.add(s);
			
			return decode(list);
		}
	}
	
	/**
	 * @param dn DocNature
	 * @return A identifier to the text
	 */
	private String getIdentifier (DocNature dn) {
		UniqueIdentifierFactory factory = UniqueIdentifierFactory.getInstance();
		// Getting the reference
		String reference = factory.getIdentifier(dn);
		
		return reference;
	}
	
	/**
	 * Codify text
	 * @param text Text to be codified
	 * @return Codified text
	 */
	private List<String> code (List<String> text) {
		// Coded text
		Iterable<String> it = codifier.code(codingKey, text);
		List<String> list = new LinkedList<>();
		
		for (String s : it)
			list.add(s);
		
		return list;
	}
	
	/**
	 * Decode the text
	 * @param text Text to be decoded
	 * @return Decoded text
	 */
	private List<String> decode (List<String> text) {
		// Coded text
		Iterable<String> it = codifier.decode(codingKey, text);
		List<String> list = new LinkedList<>();
		
		for (String s : it)
			list.add(s);
		
		return list;
	}
	
	/**
	 * @return A list containing all
	 * 	       the references of documents
	 * 		   received and sent by this agent
	 */
	public Iterable<String> documentReference () {
		List<String> list = new LinkedList<>();
		
		Set<String> set = fromAgent.keySet();
		for (String s : set)
			list.add(s);
		
		set = toAgent.keySet();
		for (String s: set)
			list.add(s);
		
		return list;
	}
	
	public void becomeUnavailable (String unavailable) {
			availability = Unavailable.valueOf(unavailable);
			// Notify Observers
			String s = "Agent " + name + " became " + availability().toString(); 
			AlertEvent alert = new AgentUnavailableAlert(s);
			setChanged();
			notifyObservers(alert);
	}
	
	/**
	 * Set the availability state of this agent
	 * as a participant agent in one mission
	 * @requires !isUnvailable()
	 */
	public void setInMission () {
		// The agent can only be set in one mission
		// if the previously state was different of Unavailable
		availability = InAction.IN_MISSION;
	}
	
	/**
	 * @return True if the availability of this agent
	 * 	       is Unavailable, false otherwise
	 */
	public boolean isUnvailable () {
		return availability.getClass().equals(Unavailable.class);
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		// Attributes
		sb.append("Name: ").append(name).append("    ")
		.append("Access Key: ").append(ack).append("\n")
		.append("Availability: ").append(availability.toString()).append("    ")
		.append("Codifier: ").append(codifier.getName()).append("    ")
		.append("Coding Key: ").append(codingKey).append("\n")
		.append("Spoken Languages: ");
		// Languages
		for (String lang : languages) 
			sb.append(lang).append("    ");
		
		sb.append("\n");
		sb.append("Documents: ");
		
		// Documents from Agent
		sb.append("\n").append("From the agent: ");
		Set<String> documents = fromAgent.keySet();
		if (!documents.isEmpty())
			sb.append("\n");
		for (String s : documents)
			sb.append(fromAgent.get(s).toString());
		
		// Documents toAgent
		sb.append("\n").append("To the agent: ");
		documents = toAgent.keySet();
		if (!documents.isEmpty())
			sb.append("\n");
		for (String s : documents)
			sb.append(toAgent.get(s).toString());

		return sb.toString();
	}

}
