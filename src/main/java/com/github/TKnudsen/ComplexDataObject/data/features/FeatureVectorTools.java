package com.github.TKnudsen.ComplexDataObject.data.features;

import java.util.List;

public class FeatureVectorTools {

	public static void addClassAttribute(List<AbstractFeatureVector<?, Feature<?>>> features, List<Object> labels, String classAttribute) {
		for (int i = 0; i < features.size(); i++)
			features.get(i).add(classAttribute, labels.get(i));
	}
}
