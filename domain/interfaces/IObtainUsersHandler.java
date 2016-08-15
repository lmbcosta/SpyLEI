package domain.interfaces;

public interface IObtainUsersHandler extends IObtainPwdStrengthVerifiersHandler{

	/**
	 * Which users exist?
	 * 
	 * @return An iterable collection of names of all users
	 */
	Iterable<String> getUsers();

}