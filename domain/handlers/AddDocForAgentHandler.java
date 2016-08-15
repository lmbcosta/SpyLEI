package domain.handlers;

import java.io.FileNotFoundException;
import java.io.FileReader;

import domain.Agent;
import domain.AgentCatalog;
import domain.DocNature;
import domain.User;
import domain.interfaces.IAddDocForAgentHandler;

/**
 * This Class represents a handler
 * to the operation Add document for agent
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class AddDocForAgentHandler implements IAddDocForAgentHandler {
	// User catalog
	private AgentCatalog acat;
	// Current Agent
	private Agent ag;
	
	/**
	 * Create and initialize an AddDocForAgentHandler
	 * @param acat Agent Catalog
	 * @param user Current user
	 * @requires acat != null && user != null
	 */
	public AddDocForAgentHandler (User user, AgentCatalog acat) {
		this.acat = acat;
	}
	
	@Override
	public boolean giveAgentName(String codeName, String accessK) {
		ag = acat.getAgent(codeName);
		
		if (ag != null)
			return ag.isRightAccessKey(accessK);
		else
			return false;
	}

	@Override
	public String giveDocumentName(String fileName) {
		// FilerReader
		FileReader fr;
		
		try {
			fr = new FileReader(fileName);
			return ag.createDocument(fr, DocNature.TO_AGENT);
			
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	@Override
	public void cancel() {
		System.out.println("AddDocForAgentHandler: cancel()");
	}

}
