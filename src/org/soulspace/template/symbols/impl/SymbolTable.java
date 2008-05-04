/**
 * File: SymbolTable.java 
 *
 * Created on Feb 14, 2003
 */
package org.soulspace.template.symbols.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;

/**
 * SymbolTable is a Map based implementation of ISymbolTable.
 * 
 * @author soulman
 * 
 */
public class SymbolTable implements ISymbolTable {

	private Map<String, ISymbol> symbolTable;

	/**
	 * Constructor
	 */
	public SymbolTable() {
		symbolTable = new HashMap<String, ISymbol>();
	}

	/**
	 * Adds a new string symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewStringSymbol(String name, String data) {
		StringSymbol stringSymbol = new StringSymbol(data);
		addSymbol(name, stringSymbol);
	}

	/**
	 * Adds a new numeric symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewNumericSymbol(String name, String data) {
		NumericSymbol numericSymbol = new NumericSymbol(data);
		addSymbol(name, numericSymbol);
	}

	/**
	 * Adds a new list symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewListSymbol(String name, List<ISymbol> data) {
		ListSymbol listSymbol = new ListSymbol(data);
		addSymbol(name, listSymbol);
	}

	/**
	 * Adds a new map symbol to the symbol table
	 * @param name the name of the symbol
	 * @param data the value of the symbol
	 */
	public void addNewMapSymbol(String name, ISymbolTable data) {
		MapSymbol mapSymbol = new MapSymbol(data);
		addSymbol(name, mapSymbol);
	}

	/**
	 * Adds a given symbol to the symbol table
	 * @param name the name of the symbol
	 * @param symbol the symbol to add
	 */
	public void addSymbol(String name, ISymbol symbol) {
		this.symbolTable.put(name, symbol);
	}
	

	/**
	 * Retrieves a symbol by name
	 * @param name the name of the symbol to retrieve
	 * @return ISymbol - symbol, if symbol is found - null if symbol could not be found
	 */
	public ISymbol getSymbol(String name) {
    if(name != null && !name.equals("")) {
      return (ISymbol) symbolTable.get(name);      
    }
    return null;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.templates.ISymbolTable#getSymbolCount()
	 */
	public int getSymbolCount() {
		return symbolTable.size();	
	}

}
