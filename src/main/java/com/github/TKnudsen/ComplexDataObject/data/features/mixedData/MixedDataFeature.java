package com.github.TKnudsen.ComplexDataObject.data.features.mixedData;

import com.github.TKnudsen.ComplexDataObject.data.features.Feature;
import com.github.TKnudsen.ComplexDataObject.data.features.FeatureType;

/**
 * <p>
 * Title: MixedDataFeature
 * </p>
 * 
 * <p>
 * Description: Single feature, e.g. for a MixedDataVector. Can be numerical,
 * categorical, or binary.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015-2016
 * </p>
 * 
 * @author Juergen Bernard
 * @version 1.0
 */
public class MixedDataFeature extends Feature<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432718477169096454L;

	/**
	 *
	 * @param featureName
	 * @param featureValue
	 */
	public MixedDataFeature(String featureName, Object featureValue) {
		super(featureName, featureValue);
		this.featureType = MixedDataFeatureTools.guessFeatureType(featureValue);
	}

	/**
	 * 
	 * @param featureName
	 * @param featureValue
	 * @param featureType
	 */
	public MixedDataFeature(String featureName, Object featureValue, FeatureType featureType) {
		super(featureName, featureValue);
		this.featureType = featureType;
	}

	@Override
	/**
	 * enables setting the featureValue. precondition: featureType match!
	 * 
	 * @param featureValue
	 * @return
	 */
	public boolean setFeatureValue(Object featureValue) {
		if (featureValue == null) {
			this.featureValue = featureValue;
			return true;
		}

		FeatureType featureType = MixedDataFeatureTools.guessFeatureType(featureValue);
		if (this.featureType.equals(featureType)) {
			this.featureValue = featureValue;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "MixedDataFeature " + featureName + ": " + featureValue + " (" + featureType.name() + ")";
	}

	@Override
	public Feature<Object> clone() {
		return new MixedDataFeature(featureName, featureValue, featureType);
	}
}
