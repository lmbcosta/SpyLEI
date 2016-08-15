package domain.stubs;

import java.util.Arrays;

import domain.interfaces.IReadDocAgentHandler;

/**
 * A stub implementation of the ReadDocAgentHandler
 * 
 * @author inunes, mguimas
 *
 */
public class ReadDocAgentHandler implements IReadDocAgentHandler {

	@Override
	public boolean readDoc(String codeName, String accessK) {
		System.out.println("ReadDocAgentHandler: readDoc(" + codeName +
				", " + accessK + ")");
		return !codeName.equals("fail");
	}

	@Override
	public Iterable<String> selectDoc(String ref) {
		System.out.println("ReadDocAgentHandler: selectDoc(" + ref + ")");
		return Arrays.asList("As armas e os baroes assinalados", "Que da ocidental praia lusitana",
				             "Por mares nunca dates navegados", "Passaram ainda alem da Taprobana");
	}

	@Override
	public Iterable<String> docsOfCurrentAgent() {
		System.out.println("ReadDocAgentHandler: docsOfCurrentAgent()");
		return Arrays.asList("ToAg0","FrAg0","ToAg1","ToAg2","FrAg1","ToAg3");
	}
	
	@Override
	public void cancel() {
		System.out.println("ReadDocAgentHandler: cancel()");
	}

}
