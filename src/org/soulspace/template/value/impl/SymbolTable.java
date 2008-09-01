/**
 * File: SymbolTable.java 
 *
 * Created on Feb 14, 2003
 */
package org.soulspace.template.value.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;

/**
 * SymbolTable is a Map based implementation of ISymbolTable.
 * 
 * @author soulman
 * 
 */
public class SymbolTable implements ISymbolTable {

	private Map<String, IValue> symbolTable;

	/**
	 * Constructor
	 */
	public SymbolTable() {
		symbolTable = new HashMap<String, IValue>();
	}

	/**
	 * Adds a new string symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewStringSymbol(String name, String data) {
		StringValue stringSymbol = new StringValue(data);
		addSymbol(name, stringSymbol);
	}

	/**
	 * Adds a new numeric symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewNumericSymbol(String name, String data) {
		NumericValue numericSymbol = new NumericValue(data);
		addSymbol(name, numericSymbol);
	}

	/**
	 * Adds a new list symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewListSymbol(String name, List<IValue> data) {
		ListValue listSymbol = new ListValue(data);
		addSymbol(name, listSymbol);
	}

	/**
	 * Adds a new map symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewMapSymbol(String name, ISymbolTable data) {
		MapValue mapSymbol = new MapValue(data);
		addSymbol(name, mapSymbol);
	}

	/**
	 * Adds a given symbol to the symbol table
	 * @param name the name of the symbol
	 * @param symbol the symbol to add
	 */
	public void addSymbol(String name, IValue symbol) {
		this.symbolTable.put(name, symbol);
	}
	
	public void addSymbolTable(ISymbolTable symbolTable) {
		this.symbolTable.putAll(((SymbolTable) symbolTable).symbolTable);
	}

	/**
	 * Retrieves a symbol by name
	 * @param name the name of the symbol to retrieve
	 * @return ISymbol - symbol, if symbol is found - null if symbol could not be found
	 */
	public IValue getSymbol(String name) {
    if(name != null && !name.equals("")) {
      return (IValue) symbolTable.get(name);      
    }
    return null;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.templates.ISymbolTable#getSymbolCount()
	 */
	public int getSymbolCount() {
		return symbolTable.size();	
	}
	
	public Set<String> getKeySet() {
		return symbolTable.keySet();
	}
}
