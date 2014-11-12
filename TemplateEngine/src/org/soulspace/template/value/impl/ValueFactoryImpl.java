/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.value.impl;

import java.util.List;

import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

public class ValueFactoryImpl {

	public Value createValue(ValueType type) {
		Value value = null;
		
		if(type.equals(ValueType.STRING)) {
			value = createStringValue();
		} else if(type.equals(ValueType.NUMERIC)) {
			value = createNumericValue();
		} else if(type.equals(ValueType.LIST)) {
			value = createListValue();
		} else if(type.equals(ValueType.MAP)) {
			value = createMapValue();
		} else if(type.equals(ValueType.METHOD)) {
//			value = createMethodVelue();
		} else if(type.equals(ValueType.ANY)) {
			value = createStringValue();
		}
		return value;
	}
	
	public StringValue createStringValue() {
		return new StringValueImpl();
	}

	public StringValue createStringValue(String data) {
		return new StringValueImpl(data);
	}

	public NumericValue createNumericValue() {
		return new NumericValueImpl();
	}
	
	public NumericValue createNumericValue(String data) {
		return new NumericValueImpl(data);
	}
	
	public NumericValue createNumericValue(double data) {
		return new NumericValueImpl(data);
	}
	
	public ListValue createListValue() {
		return new ListValueImpl();
	}

	public ListValue createListValue(List<Value> data) {
		return new ListValueImpl(data);
	}
	
	public MapValue createMapValue() {
		return new MapValueImpl();
	}
	
	public MapValue createMapValue(SymbolTable data) {
		return new MapValueImpl(data);
	}
	
}
