package domain.interfaces;

/**
 * The interface for the login use case
 * 
 * @author fmartins
 *
 */
public interface ILoginHandler extends IObtainUsersHandler {

	/**
	 * User login
	 * @param username The username of the user that wants to login
	 * @param password The password associated with the username
	 * @return true if the user successfully logs in 
	 */
	boolean login (String username, String password);
}
