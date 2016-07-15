package com.github.TKnudsen.ComplexDataObject.data.examples;

import java.io.IOException;
import java.util.List;

import com.github.TKnudsen.ComplexDataObject.data.ComplexDataObject;
import com.github.TKnudsen.ComplexDataObject.model.io.parsers.examples.TitanicParser;

/**
 * <p>
 * Title: DataFactory
 * </p>
 * 
 * <p>
 * Description: Provides Lists of ComplexDataObject instances for some given
 * data sets.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015-2016
 * </p>
 * 
 * @author Juergen Bernard
 * @version 1.02
 */
public class DataFactory {

	public static List<ComplexDataObject> createTitanicDataSet() {
		List<ComplexDataObject> titanicData = null;

		TitanicParser p = new TitanicParser("", true);
		try {
			titanicData = p.parse("data/titanic_extended.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return titanicData;
	}
}
