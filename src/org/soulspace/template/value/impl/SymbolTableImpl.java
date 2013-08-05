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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;

/**
 * SymbolTableImpl is a Map based implementation of SymbolTable.
 * 
 * @author soulman
 * 
 */
public class SymbolTableImpl implements SymbolTable {

	private Map<String, Value> symbolTable;

	/**
	 * Constructor
	 */
	public SymbolTableImpl() {
		symbolTable = new HashMap<String, Value>();
	}

	public void removeSymbol(String name) {
		symbolTable.remove(name);
	}

	/**
	 * Adds a new string symbol to the symbol table
	 * 
	 * @param name
	 *            the name of the symbol
	 * @param data
	 *            the value of the symbol
	 */
	public void addStringValue(String name, String data) {
		StringValueImpl stringSymbol = new StringValueImpl(data);
		addSymbol(name, stringSymbol);
	}

	/**
	 * Adds a new numeric symbol to the symbol table
	 * 
	 * @param name
	 *            the name of the symbol
	 * @param data
	 *            the value of the symbol
	 */
	public void addNumericValue(String name, String data) {
		NumericValueImpl numericSymbol = new NumericValueImpl(data);
		addSymbol(name, numericSymbol);
	}

	/**
	 * Adds a new list symbol to the symbol table
	 * 
	 * @param name
	 *            the name of the symbol
	 * @param data
	 *            the value of the symbol
	 */
	public void addListValue(String name, List<Value> data) {
		ListValueImpl listSymbol = new ListValueImpl(data);
		addSymbol(name, listSymbol);
	}

	/**
	 * Adds a new map symbol to the symbol table
	 * 
	 * @param name
	 *            the name of the symbol
	 * @param data
	 *            the value of the symbol
	 */
	public void addMapValue(String name, SymbolTable data) {
		MapValueImpl mapSymbol = new MapValueImpl(data);
		addSymbol(name, mapSymbol);
	}

	public void addMethodValue(String name, String methodName) {
		MethodValueImpl methodValue = new MethodValueImpl(methodName);
		addSymbol(name, methodValue);
	}

	/**
	 * Adds a given symbol to the symbol table
	 * 
	 * @param name
	 *            the name of the symbol
	 * @param symbol
	 *            the symbol to add
	 */
	public void addSymbol(String name, Value symbol) {
		this.symbolTable.put(name, symbol);
	}

	public void addSymbolTable(SymbolTable symbolTable) {
		this.symbolTable.putAll(((SymbolTableImpl) symbolTable).symbolTable);
	}

	/**
	 * Retrieves a symbol by name
	 * 
	 * @param name
	 *            the name of the symbol to retrieve
	 * @return ISymbol symbol, if symbol is found, null if symbol could not be
	 *         found
	 */
	public Value getSymbol(String name) {
		if (name != null && !name.equals("")) {
			return (Value) symbolTable.get(name);
		}
		return null;
	}

	public int getSymbolCount() {
		return symbolTable.size();
	}

	public Set<String> getKeySet() {
		return symbolTable.keySet();
	}

	public String toString() {
		return symbolTable.toString();
	}

}
