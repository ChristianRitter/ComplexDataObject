package main.java.com.github.TKnudsen.ComplexDataObject.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * <p>
 * Title: ComplexDataObject
 * </p>
 * 
 * <p>
 * Description: ComplexDataObject is a key-value store that can be used to model
 * complex real-world objects.
 * 
 * For the use of ComplexDataObject in combination with DB solutions some
 * constructors allow the definition of the ID from an external competence.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015-2016
 * </p>
 * 
 * @author Juergen Bernard
 * @version 1.01
 */
public class ComplexDataObject implements IKeyValueStore, Iterable<String> {

	protected Long ID;
	protected String name;
	protected String description;

	protected SortedMap<String, Object> attributes = new TreeMap<String, Object>();

	public ComplexDataObject() {
		this.ID = getRandomLong();
	}

	public ComplexDataObject(Long ID) {
		if (ID == null)
			throw new IllegalArgumentException("ID was null");
		this.ID = ID;
	}

	public ComplexDataObject(String name, String description) {
		this.ID = getRandomLong();
		this.name = name;
		this.description = description;
	}

	public ComplexDataObject(Long ID, String name, String description) {
		if (ID == null)
			throw new IllegalArgumentException("ID was null");
		this.ID = ID;
		this.name = name;
		this.description = description;
	}

	/**
	 * Little helper for the generation of a unique identifier.
	 * 
	 * @return unique ID
	 */
	private Long getRandomLong() {
		return UUID.randomUUID().getMostSignificantBits();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> keySet() {
		return attributes.keySet();
	}

	@Override
	public Iterator<String> iterator() {
		return attributes.keySet().iterator();
	}

	@Override
	public boolean remove(String attribute) {
		if (attributes.get(attribute) != null) {
			attributes.remove(attribute);
			return true;
		}
		return false;
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

	public String toStringInLine() {
		String output = "";
		for (String key : attributes.keySet())
			output += (key + attributes.get(key).toString() + "/t");
		return output;
	}

	@Override
	public Long getID() {
		return ID;
	}

	@Override
	public int size() {
		if (attributes == null)
			return 0;
		return attributes.size();
	}

	@Override
	public String getName() {
		if (name == null)
			return toString();
		return name;
	}

	@Override
	public String getDescription() {
		if (description == null)
			return toString();
		return description;
	}

	@Override
	public void add(String attribute, Object value) {
		attributes.put(attribute, value);
	}

	@Override
	public Object get(String attribute) {
		return attributes.get(attribute);
	}

	@Override
	public Class<?> getType(String attribute) {
		if (attributes.get(attribute) != null)
			return attributes.get(attribute).getClass();
		return null;
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
	public int hashCode() {
		return ID.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ComplexDataObject other = (ComplexDataObject) obj;
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

		if (size() != other.size())
			return false;

		Set<String> keys = attributes.keySet();
		keys.addAll(other.keySet());

		for (String string : keys)
			if (!get(string).equals(other.get(string)))
				return false;

		return true;
	}
}
