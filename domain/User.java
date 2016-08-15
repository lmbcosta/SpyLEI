package domain;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedList;

/**
 * This Class represents an User
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class User implements Observer {
	// Name
	private String name;
	// Map of Agents
	private Map<String, Agent> agents;
	// Map of Missions
	private Map<String, Mission> missions;
	// List of Alerts
	 private List<AlertEvent> alerts;
	// Access key
	private String ack;
	
	/**
	 * Create and initialize an User
	 * @param name User´s name
	 * @param pwd User's password
	 * @requires name != null && pwd != null
	 */
	public User (String name, String pwd) {
		agents = new HashMap<>();
		missions = new HashMap<>();
		alerts = new LinkedList<>();
		this.name = name;
		ack = pwd;
	}
	
	/**
	 * @return Name of this User
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Adds an agent to this User
	 * @param ag Agent to add
	 * @requires ag != null
	 */
	public void addAgent (Agent ag) {
		// Add this user has observer of ag
		ag.addObserver(this);
		agents.put(ag.getName(), ag);
	}
	
	/**
	 * Add a mission to this User
	 * @param mi Mission to add
	 * @requires mi != null
	 */
	public void addMission (Mission mi) {
		// Add this user has observer of mi
		mi.addObserver(this);
		missions.put(mi.getName(), mi);
	}
	
	/**
	 * @param pwd Access password
	 * @return True if pwd is the correct access password
	 * 		   false otherwise
	 */
	public boolean isRightPassword (String pwd) {
		return ack.equals(pwd);
	}
	
	/**
	 * Check if this User contains Agent ag
	 * @param ag Agent to search
	 * @return True if the user contains
	 * 		   this agent, false otherwise
	 */
	public boolean hasAgent (Agent ag) {
		return agents.containsKey(ag.getName());
	}
	
	@Override
	public void update(Observable o, Object arg) {
			
		alerts.add((AlertEvent)arg);
		
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ").append(name).append("    ").
		append("Pwd: ").append(ack).append("\n");
		
		sb.append("Has access to the following agents:").append("\n");
		// Append Agents
		Set<String> setAg = agents.keySet();
		for (String s : setAg)
			sb.append(agents.get(s).toString()).append("\n");
		
		// Append Missions
		sb.append("Has access to the following missions:").append("\n");
		Set<String> setMi = missions.keySet();
		for (String s : setMi)
			sb.append(missions.get(s).toString()).append("\n");
		
		// Append Alerts
		sb.append("Has received the following alerts: ");
		for (AlertEvent alert : alerts)
			sb.append("\n").append(alert.getMessage());
		
		return sb.toString();
	}	
}
