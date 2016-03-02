package com.github.TKnudsen.ComplexDataObject.preprocessing;

import java.util.Arrays;
import java.util.List;

import com.github.TKnudsen.ComplexDataObject.tools.ComplexDataObjectFactory;
import com.github.TKnudsen.ComplexDataObject.data.ComplexDataContainer;
import com.github.TKnudsen.ComplexDataObject.data.ComplexDataObject;
import com.github.TKnudsen.ComplexDataObject.preprocessing.DoubleConverter;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class DoubleConverterTest {

	@Test
	public void shouldProcessDataObjectContainers() {
		final List<ComplexDataObject> objects = Arrays.asList(
				ComplexDataObjectFactory.createObject("A", new Double(2.0), "B", "1,2"),
				ComplexDataObjectFactory.createObject("A", new Double(3.0), "B", "3,3")
		);

		final ComplexDataContainer complexDataContainer = new ComplexDataContainer(objects);

		DoubleConverter doubleConverter = new DoubleConverter("B", "B'", ',', '.');
		doubleConverter.process(complexDataContainer);

		complexDataContainer.forEach(dataObject -> assertThat(dataObject.get("B'")).isOfAnyClassIn(Double.class));
	}
}
