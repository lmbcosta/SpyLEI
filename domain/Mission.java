package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Map;
import java.util.HashMap;
import java.util.Observer;

/**
 * This Class represents a Mission
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class Mission extends Observable implements Observer {
	// Mission name
	private String name;
	// Access Key
	private String ack;
	// Agent Responsible
	private Agent responsible;
	// Agents that participating
	// on this mission
	private Map<String, Agent> agents;
	// List of particular characteristics
	private List<String> charac;
	
	/**
	 * Create and initialize a Mission
	 * @param name Mission´s name
	 * @requires name != null
	 */
	public Mission (String name) {
		this.name = name;
		agents = new HashMap<>();
		charac = new LinkedList<>();
	}
	
	/**
	 * Set access key to this Agent
	 * @param ack Access key
	 * @requires ack != null
	 */
	public void setAccessKey (String ack) {
		this.ack = ack;
	}
	
	/**
	 * @return The name of this Mission
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Check if key is the correct Access Key
	 * @param key Key to test
	 * @requires key != null
	 * @return True if key is the access key,
	 * 		   false otherwise		
	 */
	public boolean  isRightAccessKey (String key) {
		return ack.equalsIgnoreCase(key);
	}
	
	/**
	 * Set an Agent responsible
	 * to this Mission
	 * @param ag Agent responsible
	 * @requires ag != null
	 */
	public void setResponsible (Agent ag) {
		// If ag state is Unavailable
		// it's not possible to set as responsible
		
		if (!ag.isUnvailable()) {
			responsible = ag;
			// Change the state to IN_MISSION
			ag.setInMission();
			// Add this mission as an Observer
			// of his own responsible
			ag.addObserver(this);
		}
	}
	
	/**
	 * Add a participant Agent
	 * to this Mission
	 * @param ag Participant Agent
	 * @requires ag != null
	 */
	public void setParticipationAgent (Agent ag) {
		// If ag state is Unavailable
		// it's not possible to set as a participant
		
		if (!ag.isUnvailable()) {
			// Change state to IN_MISSION
			ag.setInMission();
			// Add this mission as an Observer
			ag.addObserver(this);
			agents.put(ag.getName(), ag);
		}
	}
	
	/**
	 * Add a new characteristic to this Mission
	 * @param keyword New Characteristic
	 */
	public void setCharacteristic (String keyword) {
		charac.add(keyword);
	}
	
	/**
	 * @return An iterable containing characteristics
	 * 		   belonged to this Mission
	 */
	public Iterable<String> getCharacteristics () {
		return charac;
	}
	
	/**
	 * @return The name of the responsible
	 * 	       of this Mission, 
	 * 		   null is there is no responsible
	 */
	public String getResponsible () {
		// Check if there is a responsible
		return responsible != null ? responsible.getName() : null;
	}
	
	/**
	 * @return An iterable containing
	 * 	       the name of all agents
	 * 		   that participate on this Mission
	 */
	public Iterable<String> getParticipants () {
		String s = "Mission " + name + " info was searched";
		// Notify Observers
		setChanged();
		notifyObservers(new MissionInfoSearchedAlert(s));
		return agents.keySet();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Check the Alert
		if (arg.getClass().equals(AgentUnavailableAlert.class)) {
			Agent ag = (Agent)o;
			// Check if the Agent is the responsible
			if (responsible != null && ag.getName().equals(responsible.getName())) {
					responsible = null;
			}
			else {
				// Is a participant 
				// Remove from the participants
				agents.remove(ag.getName());
			}
				
			// Inform the observable object that
			// this Mission is no longer observer of Agent ag
			ag.deleteObserver(this);
		}
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ").append(name).append("    ").
		append("AccessKey: ").append(ack).append("\n").
		append("Responsible agent: ").append(responsible.getName()).
		append("\n").append("Keywords: ");
		for (String s : charac)
			sb.append(s).append("    ");
		sb.append("\n");
		// Participating Agents
		sb.append("Participating Agents: ");
		if (!agents.keySet().isEmpty())
			sb.append("\n");
		for (String s : agents.keySet())
			sb.append(agents.get(s).toString());
		
		return sb.toString();
	}
}
