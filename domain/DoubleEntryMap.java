package domain;

import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a double entry map
 * that can be operated by key and value
 * 
 * @author Luis Costa
 * @author Manuel Caldeira
 * @author Pedro Caldeira
 *
 */
public class DoubleEntryMap<K, V> {
	private Map<K, V> keyValue;
	private Map<V, K> valueKey;
	
	/**
	 * Create and initialize a DoubleEntryMap
	 */
	public DoubleEntryMap () {
		keyValue = new HashMap<>();
		valueKey = new HashMap<>();
	}
	
	/**
	 * Add a pair key value to this DoubleEntryMap
	 * @param key Key of the pair
	 * @param value Value of the pair
	 */
	public void add(K key, V value) {
		keyValue.put(key, value);
		valueKey.put(value, key);
	}
	
	/**
	 * @param key Key of the entry 
	 * @return The value associated to 
	 *         the entry who has key as key
	 */
	public V getValue(K key) {
		return keyValue.get(key);
	}
	
	/**
	 * @param value Value of the entry
	 * @return The key associated to 
	 * 	       the entry who has value as value
	 */
	public K getKey(V value) {
		return valueKey.get(value);
	}
}

