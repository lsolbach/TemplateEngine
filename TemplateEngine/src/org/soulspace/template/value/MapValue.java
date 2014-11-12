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

public interface MapValue extends Value {

	/**
	 * Returns the symbol table
	 * 
	 * @return SymbolTableImpl
	 */
	SymbolTable getData();

	/**
	 * Sets the data.
	 * 
	 * @param data
	 *            The data to set
	 */
	void setData(SymbolTable data);
	
	/**
	 * adds a value under the given key
	 * @param name
	 * @param value
	 */
	void addValue(String key, Value value);
	
	/**
	 * Gets the value for the given name.
	 * Returns null if there's no value for the given name.
	 * @param key
	 * @return
	 */
	Value getValue(String key);
	
}