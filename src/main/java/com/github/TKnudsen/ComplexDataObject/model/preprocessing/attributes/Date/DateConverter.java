package com.github.TKnudsen.ComplexDataObject.model.preprocessing.attributes.Date;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.TKnudsen.ComplexDataObject.data.complexDataObject.ComplexDataContainer;
import com.github.TKnudsen.ComplexDataObject.data.complexDataObject.ComplexDataObject;
import com.github.TKnudsen.ComplexDataObject.model.io.parsers.objects.DateParser;
import com.github.TKnudsen.ComplexDataObject.model.preprocessing.complexDataObject.DataProcessingCategory;
import com.github.TKnudsen.ComplexDataObject.model.preprocessing.complexDataObject.IComplexDataObjectProcessor;

public class DateConverter implements IComplexDataObjectProcessor {

	private String attribute;

	private DateParser dateParser = new DateParser();

	public DateConverter() {
		this.attribute = null;
	}

	public DateConverter(String attribute) {
		this.attribute = attribute;
	}

	public void process(ComplexDataContainer container) {
		if (attribute == null)
			throw new IllegalArgumentException("DateConverter requires attribute definition first.");

		// strategy: parse to double and buffer the values
		// then remove attribute and re-create attribute in the container
		Map<ComplexDataObject, Date> values = new HashMap<>();
		for (ComplexDataObject cdo : container) {
			if (cdo.get(attribute) != null) {
				Date d = dateParser.apply(cdo.get(attribute));
				values.put(cdo, d);
			} else
				values.put(cdo, null);
		}

		container.remove(attribute);
		container.addAttribute(attribute, Date.class, null);
		for (ComplexDataObject cdo : container)
			cdo.add(attribute, values.get(cdo));
	}

	@Override
	public void process(List<ComplexDataObject> data) {
		if (attribute == null)
			throw new IllegalArgumentException("DateConverter requires attribute definition first.");

		for (ComplexDataObject cdo : data) {
			if (cdo.get(attribute) != null) {
				Date d = dateParser.apply(cdo.get(attribute));
				cdo.add(attribute, d);
			} else
				cdo.add(attribute, null);
		}
	}

	@Override
	public DataProcessingCategory getPreprocessingCategory() {
		return DataProcessingCategory.DATA_CLEANING;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}