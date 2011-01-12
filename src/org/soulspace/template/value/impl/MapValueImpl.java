/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

/**
 * Map Symbol, stores SymbolTables.
 * 
 * @author soulman
 */
public class MapValueImpl implements Value, MapValue {

	private SymbolTable data;

	public MapValueImpl() {
		this.data = new SymbolTableImpl();
	}

	public MapValueImpl(SymbolTable symbolTable) {
		this.data = symbolTable;
	}

	public ValueType getType() {
		return ValueType.MAP;
	}

	public SymbolTable getData() {
		return data;
	}

	public void setData(SymbolTable data) {
		this.data = data;
	}

	public String evaluate() {
		String result = String.valueOf(getData().getSymbolCount());

		return result;
	}

	public boolean isTrue() {
		return data.getSymbolCount() != 0;
	}

	public void addValue(String name, Value value) {
		data.addSymbol(name, value);
	}

	public Value getValue(String name) {
		return data.getSymbol(name);
	}

}
