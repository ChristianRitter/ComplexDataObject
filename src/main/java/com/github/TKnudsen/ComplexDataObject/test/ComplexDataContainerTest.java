package main.java.com.github.TKnudsen.ComplexDataObject.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.github.TKnudsen.ComplexDataObject.data.ComplexDataContainer;
import main.java.com.github.TKnudsen.ComplexDataObject.data.ComplexDataObject;
import main.java.com.github.TKnudsen.ComplexDataObject.tools.ComplexDataObjectFactory;

public class ComplexDataContainerTest {

	public static void main(String[] args) {

		ComplexDataObject a = ComplexDataObjectFactory.createObject("Att A", new Double(2.0), "Att B", "asdf");
		ComplexDataObject b = ComplexDataObjectFactory.createObject("Att A", new Double(3.0), "Att B", "jkl�");

		List<ComplexDataObject> objects = new ArrayList<>(Arrays.asList(a, b));

		ComplexDataContainer complexDataContainer = new ComplexDataContainer(objects);
		for (ComplexDataObject complexDataObject : complexDataContainer) {
			System.out.println(complexDataObject);
		}
	}
}
