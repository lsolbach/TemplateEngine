/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.value;

import java.util.List;
import java.util.Set;

/**
 * Interface for symbol tables.
 * 
 * @author soulman
 * 
 */
public interface SymbolTable {

	/**
	 * Create and add a new StringValue to the symbol table
	 * 
	 * @param name
	 *            Name of the new symbol
	 * @param data
	 *            Data to store in this Symbol
	 */
	public void addStringValue(String name, String data);

	/**
	 * Create and add a new NumericValue to the symbol table
	 * 
	 * @param name
	 *            Name of the new symbol
	 * @param data
	 *            Data to store in this Symbol
	 */
	public void addNumericValue(String name, String data);

	/**
	 * Create and add a new ListValue to the symbol table
	 * 
	 * @param name
	 *            Name of the new symbol
	 * @param data
	 *            Data to store in this Symbol
	 */
	public void addListValue(String name, List<Value> data);

	/**
	 * Create and add a new MapValue to the symbol table
	 * 
	 * @param name
	 *            Name of the new symbol
	 * @param data
	 *            Data to store in this Symbol
	 */
	public void addMapValue(String name, SymbolTable data);

	/**
	 * Create and add a new MethodValue to the symbol table
	 * 
	 * @param name
	 *            Name of the new symbol
	 * @param data
	 *            Data to store in this Symbol
	 */
	public void addMethodValue(String name, String methodName);

	/**
	 * Add a symbol to the symbol table
	 * 
	 * @param name
	 *            Name of the symbol
	 * @param symbol
	 *            Symbol to add
	 */
	public void addSymbol(String name, Value symbol);

	/**
	 * Merges a symbol table with this symbol table
	 * 
	 * @param symbolTable
	 */
	public void addSymbolTable(SymbolTable symbolTable);

	/**
	 * Returns the symbol with this name
	 * 
	 * @param name
	 *            Name of the symbol to retrieve
	 * @return
	 */
	public Value getSymbol(String name);

	/**
	 * Returns the number of symbols in this symbol table
	 * 
	 * @return Number of symbols
	 */
	public int getSymbolCount();

	/**
	 * Returns a Set of the keys of this symbol table
	 * 
	 * @return set of keys
	 */
	public Set<String> getKeySet();

	public void removeSymbol(String name);

}