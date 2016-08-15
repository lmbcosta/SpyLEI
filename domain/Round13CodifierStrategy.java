package domain;

import java.util.List;
import java.util.LinkedList;

/**
 * This class represents a codifier strategy to code/decode text using Caesar
 * cipher with key equals 13
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class Round13CodifierStrategy extends AbstractCodifierStrategy {
	// Name of the codifier strategy
	private static final String NAME = "Round13";
	// String containing all the letters to code and decode
	private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
	// Size of LETTERS
	private static final int SIZE = LETTERS.length();
	// Shift size
	private static final int THIRTEEN = 13;
	// Map containing code / decode entries
	private DoubleEntryMap<Character, Character> map;

	/**
	 * Create and initialize a Round13CodifersStrategy
	 */
	public Round13CodifierStrategy() {
		super(NAME);
		map = new DoubleEntryMap<>();

		// Add the corresponding pair
		for (int i = 0; i < SIZE; i++)
			map.add(LETTERS.charAt(i), LETTERS.charAt((i + THIRTEEN) % SIZE));
	}

	@Override
	public Iterable<String> code(String key, Iterable<String> text) {
		List<String> list = new LinkedList<>();

		for (String s : text)
			list.add(codeDecodeString(s, true));
		return list;
	}

	@Override
	public Iterable<String> decode(String key, Iterable<String> text) {
		List<String> list = new LinkedList<>();

		for (String s : text)
			list.add(codeDecodeString(s, false));
		return list;
	}

	/**
	 * Code or decode the string s
	 * 
	 * @param s
	 *            String to code/Decode
	 * @param m
	 *            Boolean that identifies an operation: true for code, false to
	 *            decode
	 * @return String coded/decoded
	 * @requires s != null
	 */
	private String codeDecodeString(String s, boolean m) {
		boolean isEqual = m;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (isEqual)
				sb.append(codeChar(s.charAt(i)));
			else
				sb.append(decodeChar(s.charAt(i)));
		}
		return sb.toString();
	}

	/**
	 * Code a char c giving the next 13th char a..z / A..Z z -> A / Z -> A
	 * 
	 * @param c
	 *            Char to be coded
	 * @requires c isIn ['a'..'z'] || c isIn ['A'..'Z']
	 * @return the corresponding code to char c
	 */
	private char codeChar(char c) {
		boolean isLowerCase = Character.isLowerCase(c);
		char c1 = isLowerCase ? c : Character.toLowerCase(c);

		if (!(map.getValue(c1) == null))
			c1 = map.getValue(c1);
		else
			c1 = c;

		return isLowerCase ? c1 : Character.toUpperCase(c1);
	}

	/**
	 * Decode a char c giving the next 13th char a..z / A..Z z -> A / Z -> A
	 * 
	 * @param c
	 *            Char to be coded
	 * @requires c isIn ['a'..'z'] || c isIn ['A'..'Z']
	 * @return the corresponding code to char c
	 */
	private char decodeChar(char c) {
		boolean isLowerCase = Character.isLowerCase(c);
		char c1 = isLowerCase ? c : Character.toLowerCase(c);

		if (!(map.getKey(c1) == null))
			c1 = map.getKey(c1);
		else
			c1 = c;

		return isLowerCase ? c1 : Character.toUpperCase(c1);
	}
}
