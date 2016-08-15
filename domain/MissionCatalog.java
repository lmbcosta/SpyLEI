package domain;

import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a missions catalog
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class MissionCatalog {
	private Map<String, Mission> missions;
	
	/**
	 * Create and initialize a MissionCatalog
	 */
	public MissionCatalog() {
		missions = new HashMap<>();
	}
	
	/**
	 * @param name Name of the mission to return
	 * @return Mission with the name name, 
	 * @requires name != null && existsMission(name)
	 */
	public Mission getMission (String name) {
		 return missions.get(name);
	}
	
	/**
	 * Check if exists a mission with name name
	 * @param name Mission's name
	 * @return True if exists a Mission with name name,
	 * 		   false, otherwise
	 * @requires name != null
	 */
	public boolean existsMission(String name) {
		return missions.containsKey(name);
	}
	
	/**
	 * Add a new mission to this MissionCatalog
	 * @param mi Mission to add
	 * @requires mi != null
	 */
	public void newMission (Mission mi) {
		missions.put(mi.getName(), mi);
	}
}
