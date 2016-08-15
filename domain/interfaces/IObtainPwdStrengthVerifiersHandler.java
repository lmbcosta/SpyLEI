package domain.interfaces;

public interface IObtainPwdStrengthVerifiersHandler {

	/**
	 * Which password strength verifiers exist available?
	 * 
	 * @return An iterable collection of names of verifiers
	 */
	Iterable<String> getPwdStrengthVerifiers();

}
