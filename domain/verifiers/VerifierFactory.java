package domain.verifiers;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory to create password strength verifier adapters 
 * Notice a different way of implementing a singleton. 
 * Confront with the services.SessionManager class.
 * 
 * @author Isabel Nunes
 *
 */
public enum VerifierFactory {
	INSTANCE;

	// the known password strength verifier adapters
	private Map<String,IPwdStrengthAdapter> verifiers;
	
	/**
	 * Constructs the factory loading the available verifier classes from
	 * folder bin/domain.verifiers. The loader checks that the verifiers implement
	 * the AbstractPwdStrengthAdapter interface. 
	 *
	 */
	private VerifierFactory() {
		verifiers = new HashMap<String, IPwdStrengthAdapter> ();
		loadVerifiers();
	}
		
	/**
	 * Loads all classes from folder /bin/domain.verifiers that descend from 
	 * AbstractPwdStrengthAdapter and creates an instance of each one.
	 */
	private void loadVerifiers() {
		// add filters in the filters folder
		String separator = System.getProperty("file.separator");
		File filtersFolder = new File(System.getProperty("user.dir") + separator + "bin" + 
				separator + "domain" + separator + "verifiers");
		File [] classes = filtersFolder.listFiles(new FilenameFilter () {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		verifiers.put("Dummy", new DummyAdapter());
		for (File className : classes) {
			try {
				String s = className.getName();
				Class<?> verifierClass = 
					Class.forName("domain.verifiers." + s.substring(0, s.lastIndexOf('.')));
		    	if (verifierClass.getGenericSuperclass() == AbstractPwdStrengthAdapter.class &&
		    			!className.equals("Dummy")) {
					IPwdStrengthAdapter verifier = (IPwdStrengthAdapter) verifierClass.newInstance();
					verifiers.put( verifier.getName(), verifier);
				}		    
			} catch (Exception e) {
				// Do nothing! Just ignore the class;
			}
		}
	}
	
	/**
	 * Find a password strength verifier adapter by name
	 * 
	 * @param name The name of the adapter to look for
	 * @requires name != null
	 * @return The adapter with the specified name, in case it exists; 
	 *         otherwise a DummyAdapter instance is returned
	 */
	public IPwdStrengthAdapter getPwdStrengthAdapter(String name) {
		try {
		    return verifiers.get(name);
		} catch (Exception e) {
			return verifiers.get("Dummy");
		}
	}

	/**
	 * @return The collection of available adapter names
	 */
	public Iterable<String> pwdStrengthAdaptersList() {
		return verifiers.keySet();
	}
}
