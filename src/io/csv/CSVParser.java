package io.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.math3.exception.NullArgumentException;

import data.ComplexDataObject;
import io.ParserTools;
import io.arff.WekaTools;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class CSVParser implements ComplexDataObjectParser {

	private String missingValueIndicator;

	public CSVParser(String missingValueIndicator) {
		this.missingValueIndicator = missingValueIndicator;
	}

	@Override
	public List<ComplexDataObject> parse(String filename) throws IOException {

		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(filename));
		Instances instances = loader.getDataSet();

		List<ComplexDataObject> data = new ArrayList<>();

		// Step1: create metaMapping
		Map<Integer, Entry<String, Class<?>>> metaMapping = WekaTools.getAttributeSchema(instances);

		// Step2: create ComplexDataObjects
		for (int zeile = 0; zeile < instances.numInstances(); zeile++) {

			Instance instance = instances.instance(zeile);

			ComplexDataObject complexDataObject = new ComplexDataObject();

			// parse columns
			for (Integer spalte = 0; spalte < instances.numAttributes(); spalte++) {

				Entry<String, ?> entry = WekaTools.assignEntry(metaMapping, instance, spalte, missingValueIndicator);

				if (entry != null) {
					if (entry.getValue() != null && entry.getValue() instanceof String) {
						Date date = ParserTools.parseDate((String) entry.getValue());
						if (date != null)
							complexDataObject.add(entry.getKey(), date);
						else
							complexDataObject.add(entry.getKey(), entry.getValue());
					} else
						complexDataObject.add(entry.getKey(), entry.getValue());
				} else
					throw new NullArgumentException();
			}
			data.add(complexDataObject);
		}
		return data;
	}

	public String getMissingValueIndicator() {
		return missingValueIndicator;
	}
}
