package org.soulspace.template.value.impl;

import java.util.List;

import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;

public class ValueFactoryImpl {

	IStringValue createStringValue() {
		return new StringValue();
	}

	IStringValue createStringValue(String data) {
		return new StringValue(data);
	}

	INumericValue createNumericValue() {
		return new NumericValue();
	}
	
	INumericValue createNumericValue(String data) {
		return new NumericValue(data);
	}
	
	INumericValue createNumericValue(double data) {
		return new NumericValue(data);
	}
	
	IListValue createListValue() {
		return new ListValue();
	}

	IListValue createListValue(List<IValue> data) {
		return new ListValue(data);
	}
	
	IMapValue createMapValue() {
		return new MapValue();
	}
	
	IMapValue createMapValue(ISymbolTable data) {
		return new MapValue(data);
	}
	
	
}
