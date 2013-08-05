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

import java.util.Iterator;
import java.util.List;

public interface ListValue extends Value {

	/**
	 * Create and add a new ListValue
	 * 
	 * @param data
	 */
	void addNewListValue(List<Value> data);

	/**
	 * Create and add a new MapValue
	 * 
	 * @param data
	 */
	void addNewMapValue(SymbolTable data);

	/**
	 * Create and add a new NumericValue
	 * 
	 * @param data
	 */
	void addNewNumericValue(String data);

	/**
	 * Create and add a new StringValue
	 * 
	 * @param data
	 */
	void addNewStringValue(String data);

	/**
	 * Add a symbol
	 * 
	 * @param symbol
	 */
	void addValue(Value symbol);

	/**
	 * Returns the list
	 * 
	 * @return List
	 */
	List<Value> getData();

	/**
	 * Returns the current entry
	 * 
	 * @return ISymbol
	 */
	Value getEntry();

	/**
	 * Returns the current index
	 * 
	 * @return long
	 */
	int getIndex();

	int size();

	/**
	 * Returns an Iterator for this list symbol
	 * 
	 * @return
	 */
	Iterator<Value> iterateData();

	/**
	 * Checks for other entries.
	 * 
	 * @return
	 */
	boolean hasNext();

	/**
	 * Returns the next entry
	 * 
	 * @return
	 */
	Object next();

	/**
	 * Sets the entry.
	 * 
	 * @param entry
	 *            The entry to set
	 */
	void setEntry(Value entry);

	/**
	 * Sets the index.
	 * 
	 * @param index
	 *            The index to set
	 */
	void setIndex(int index);

	void setData(List<Value> list);

}