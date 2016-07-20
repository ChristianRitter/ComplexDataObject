package com.github.TKnudsen.ComplexDataObject.model.preprocessing;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import com.github.TKnudsen.ComplexDataObject.data.ComplexDataContainer;
import com.github.TKnudsen.ComplexDataObject.data.ComplexDataObject;

/**
 * <p>
 * Title: BigDecimalConverter
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
 * @version 1.01
 */
public class BigDecimalConverter implements IPreprocessingRoutine {

	String attribute;
	String targetAttribute;

	DecimalFormat decimalFormat = null;

	public BigDecimalConverter(String attribute, String targetAttribute, Character decimalSeparator, Character thousandsSeparator) {
		this.attribute = attribute;
		this.targetAttribute = targetAttribute;

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
	}

	@Override
	public void process(ComplexDataContainer container) {
		// add new attribute to schema
		container.addAttribute(targetAttribute, BigDecimal.class, BigDecimal.ZERO);

		// parse
		for (ComplexDataObject complexDataObject : container) {
			BigDecimal bd = BigDecimal.ZERO;

			try {
				bd = (BigDecimal) decimalFormat.parse(complexDataObject.get(attribute).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			complexDataObject.add(targetAttribute, bd);
		}
	}
	
	@Override
	public PreprocessingCategory getPreprocessingCategory() {
		return PreprocessingCategory.SECONDARY_DATA_PROVIDER;
	}

}
