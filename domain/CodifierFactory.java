package domain;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;
import java.util.HashMap;

/**
 * A factory to create codifier strategies 
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class CodifierFactory {
	// The known names of codifier strategies
	private Map<String, ICodifierStrategy> codifiers;
	private static CodifierFactory INSTANCE = new CodifierFactory();
	
	/**
	 * Create and initialize the unique
	 * instance of CodifierFactory
	 */
	private CodifierFactory() {
		codifiers = new HashMap<String, ICodifierStrategy> ();
		loadCodifiers();
	}
	
	/**
	 * @return The only instance of this class 
	 */
	public static CodifierFactory getInstance () {
		return INSTANCE;
	}
	
	/**
	 * @param name Name of CodifierStrategy
	 * @return A ICodifierStrategy named name
	 * 	       or IdentityCodifierStrategy by default 
	 *         if does not exist an ICodifierStrategy 
	 *         with this name
	 */
	public ICodifierStrategy getCodifier (String name) {
		try {
		    return codifiers.get(name);
		} catch (Exception e) {
			return codifiers.get("Identity");
		}
	}
	
	/**
	 * @return Iterator containing all the codifiers available
	 */
	public Iterable<String> getCodifiersNameList () {
		Iterable<String> result = codifiers.keySet();
		
		return result;
	}
	
	/**
	 * Load the classes that implement
	 * ICodifierStrategy in Map codifiers
	 */
	private void loadCodifiers() {
		// add filters in the filters folder
		String separator = System.getProperty("file.separator");
		File filtersFolder = new File(System.getProperty("user.dir") + separator + "bin" + 
				separator + "domain");
		File [] classes = filtersFolder.listFiles(new FilenameFilter () {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		for (File className : classes) {
			try {
				String s = className.getName();
				Class<?> verifierClass = 
					Class.forName("domain." + s.substring(0, s.lastIndexOf('.')));
		    	if (verifierClass.getGenericSuperclass() == AbstractCodifierStrategy.class) {
					ICodifierStrategy verifier = (ICodifierStrategy) verifierClass.newInstance();
					codifiers.put( verifier.getName(), verifier);
				}		    
			} catch (Exception e) {
				// Do nothing! Just ignore the class;
			}
		}
	}
}
