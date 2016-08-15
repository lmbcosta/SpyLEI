package domain.stubs;

import java.util.Arrays;

import domain.interfaces.ISetUnavailabilityHandler;

/**
 * A stub implementation of the SetUnavailabilityHandler
 * 
 * @author inunes, mguimas
 *
 */
public class SetUnavailabilityHandler implements ISetUnavailabilityHandler {

	@Override
	public String setUnavailable(String codeName, String accessK) {
		System.out.println("SetUnavailabilityHandler: setUnavailable(" + codeName
				+ ", " + accessK + ")");
		return !codeName.equals("fail") ? "IN_MISSION" : null;
	}

	@Override
	public boolean selectUnavailability(String kind) {
		System.out.println("SetUnavailabilityHandler: selectUnavailability(" + kind + ")");
		return Arrays.asList("ARRESTED", "DESERTED", "ILL", "DEAD", "RETIRED").contains(kind);
	}

	@Override
	public Iterable<String> kindsOfUnavailability() {
		System.out.println("SetUnavailabilityHandler: kindsOfUnavailability()");
		return Arrays.asList("ARRESTED", "DESERTED", "ILL", "DEAD", "RETIRED");
	}
	
	@Override
	public void cancel() {
		System.out.println("SetUnavailabilityHandler: cancel()");
	}

}
