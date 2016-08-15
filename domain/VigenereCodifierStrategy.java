package domain;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a codifier strategy
 * to code/decode text using Viginere Cipher
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */

public class VigenereCodifierStrategy extends AbstractCodifierStrategy {
	private static final String NAME = "Vigenere";
	
	/**
	 * Create and initialize a VigenereCodifierStrategy
	 */
	public VigenereCodifierStrategy () {
		super(NAME);
	}
	
	@Override
	public Iterable<String> code (String key, Iterable<String> text) {
		List<String> list = new LinkedList<>();
		
		for (String s : text) 
			list.add(encryptViginere(key, s));
		
		return list;
	}
	
	@Override
	public Iterable<String> decode (String key, Iterable<String> text) {
		List<String> list = new LinkedList<>();
		
		for (String s : text)
			list.add(decryptViginere(key, s));
		
		return list;
	}
	
	/**
	 * Encrypt text using Viginere Cipher
	 * @param key Key used to encrypt the text
	 * @param text Plain text to be encrypted
	 * @return Encrypted text using Viginere Cipher
	 * 	       with key as key
	 */
	private String encryptViginere (String key, String text) {
		// Save lower upper case state of text char
		boolean [] saveCase = saveCase(text);
		String s = "";
        text = text.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') 
            	s += c;
            else {
            	s += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            }
            
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++)
        	if (!saveCase[i])
        		sb.append(Character.toLowerCase(s.charAt(i)));
        	else 
        		sb.append(s.charAt(i));
		        
        return sb.toString();
	}
	
	/**
	 * Decode text using Viginere Cipher
	 * @param key Key used to decrypt the text
	 * @param text Plain text to be encrypted
	 * @return Decoded text using Viginere Cipher
	 * 	       with key as key
	 */
	private String decryptViginere (String key, String text) {
		// Save lower upper case state of text char
		boolean [] saveCase = saveCase(text);
		String s = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') 
            	s+= c;
            else {
            	s += (char)((c - key.charAt(j) + 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++)
        	if (!saveCase[i])
        		sb.append(Character.toLowerCase(s.charAt(i)));
        	else
        		sb.append(s.charAt(i));
        
        return sb.toString();
	}
	
	/**
	 * @param s String that contains char
	 * 	      to save lower upper case state
	 * @return Boolean array when each index 
	 * 	       refers to the same index in String s,
	 * 	       true if the char is in lower case,
	 * 	       false  if the char is in upper case
	 */
	private boolean[] saveCase (String s) {
		int size = s.length();
		boolean[] saveCase = new boolean[size];
		
		for (int i = 0; i < size; i++)
			saveCase[i] = Character.isUpperCase(s.charAt(i));
		
		return saveCase;
	}
}
