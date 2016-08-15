package services;

/**
 * Singleton class for managing user sessions and for providing the
 * authenticated user to the system.
 * 
 * This is a toy implementation just for the purpose of illustrating the
 * concept.
 * 
 * @author Isabel Nunes, Mário Guimarães
 *
 */
public class SessionManager {
	// the singleton instance
	private static SessionManager INSTANCE;
	private String currentUser;

	private SessionManager() {
	}

	/**
	 * @return The lazy loaded singleton
	 */
	public static SessionManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SessionManager();
		}
		return INSTANCE;
	}

	/**
	 * Creates a session for the the username
	 * 
	 * @param username
	 *            The user name to create a session to
	 * @requires username != null
	 */
	public void createSession(String username) {
		currentUser = username;
	}

	/**
	 * Deletes the session for the current authenticated user.
	 */
	public void deleteSession() {
		currentUser = null;
	}

	/**
	 * @return The user authenticated in the current session
	 */
	public String getAuthenticatedUser() {
		return currentUser;
	}

	/**
	 * @return true if the user has been authenticated, otherwise false
	 */
	public boolean isUserAuthenticated() {
		return currentUser != null;
	}

}
