package com.github.TKnudsen.ComplexDataObject.data.features.mixedData;

import java.util.Map;

import com.github.TKnudsen.ComplexDataObject.data.features.FeatureContainer;
import com.github.TKnudsen.ComplexDataObject.data.features.FeatureSchema;

/**
 * <p>
 * Title: MixedFeatureContainer
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 *
 * @author Juergen Bernard
 * @version 1.0
 */
public class MixedDataFeatureContainer extends FeatureContainer<Object, MixedDataFeature, MixedDataFeatureVector> {

	public MixedDataFeatureContainer(FeatureSchema featureSchema) {
		super(featureSchema);
	}

	public MixedDataFeatureContainer(Map<Long, MixedDataFeatureVector> featureVectorMap) {
		super(featureVectorMap);
	}

	public MixedDataFeatureContainer(Iterable<MixedDataFeatureVector> objects) {
		super(objects);
	}
}
