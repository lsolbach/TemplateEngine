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

	public String asString() {
		return String.valueOf(data.getSymbolCount());
	}

	public long asLong() {
		return Long.valueOf(data.getSymbolCount());
	}

}
