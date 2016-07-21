package com.github.TKnudsen.ComplexDataObject.model.tools;

import com.github.TKnudsen.ComplexDataObject.data.complexDataObject.ComplexDataObject;

public class ComplexDataObjectTools {

	public static ComplexDataObject clone(ComplexDataObject object) {
		if (object == null)
			return null;

		ComplexDataObject newObject = new ComplexDataObject(object.getName(), object.getDescription());
		for (String string : object) {
			newObject.add(string, object.get(string));
		}

		return newObject;
	}
}
