package com.github.TKnudsen.ComplexDataObject.data.keyValueObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import com.github.TKnudsen.ComplexDataObject.data.complexDataObject.ComplexDataObject;
import com.github.TKnudsen.ComplexDataObject.data.interfaces.IKeyValueProvider;

/**
 * <p>
 * Title: KeyValueObject
 * </p>
 * 
 * <p>
 * Description: Basic data structure for an objects with keys/attributes and
 * values/objects.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015-2016
 * </p>
 * 
 * @author Juergen Bernard
 * @version 1.01
 */

public class KeyValueObject<V extends Object> implements IKeyValueProvider<V>, Iterable<String> {

	protected long ID;

	protected SortedMap<String, V> attributes = new TreeMap<String, V>();

	public KeyValueObject() {
		this.ID = getRandomLong();
	}

	public KeyValueObject(long ID) {
		this.ID = ID;
	}

	public KeyValueObject(Long ID) {
		if (ID == null)
			throw new IllegalArgumentException("ID was null");
	}

	@Override
	public long getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		return (int) ID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final KeyValueObject<?> other = (KeyValueObject<?>) obj;
		return this.hashCode() == other.hashCode() ? true : false;
	}

	/**
	 * Is true if the ComplexDataObject and a given Object have identical
	 * attributes and attribute values. ID, name and description are ignored.
	 * 
	 * @param obj
	 * @return
	 */
	public boolean equalValues(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ComplexDataObject other = (ComplexDataObject) obj;

		Set<String> keys = attributes.keySet();
		keys.addAll(other.keySet());

		for (String string : keys)
			if (!get(string).equals(other.get(string)))
				return false;

		return true;
	}

	@Override
	public void add(String attribute, V value) {
		attributes.put(attribute, value);
	}

	@Override
	public V get(String attribute) {
		return attributes.get(attribute);
	}

	@Override
	public Class<?> getType(String attribute) {
		if (attributes.get(attribute) != null)
			return attributes.get(attribute).getClass();
		return null;
	}

	@Override
	public Set<String> keySet() {
		return attributes.keySet();
	}

	@Override
	public Map<String, Class<?>> getTypes() {
		Map<String, Class<?>> ret = new HashMap<>();
		for (String string : attributes.keySet())
			if (attributes.get(string) == null)
				ret.put(string, null);
			else
				ret.put(string, attributes.get(string).getClass());
		return null;
	}

	@Override
	public V remove(String attribute) {
		if (attributes.get(attribute) != null)
			return attributes.remove(attribute);
		return null;
	}

	@Override
	public Iterator<String> iterator() {
		return attributes.keySet().iterator();
	}

	@Override
	public String toString() {
		String output = "";
		for (String key : attributes.keySet())
			output += (toLineString(key) + "\n");
		return output;
	}

	private String toLineString(String attribute) {
		if (attributes.get(attribute) == null)
			return "";

		String output = "";
		output += ("Attribute: " + attribute + "\t" + "Type: " + attributes.get(attribute).getClass() + "\t" + "Value: " + attributes.get(attribute));
		return output;
	}

	/**
	 * Little helper for the generation of a unique identifier.
	 * 
	 * @return unique ID
	 */
	private long getRandomLong() {
		return UUID.randomUUID().getMostSignificantBits();
	}

}
