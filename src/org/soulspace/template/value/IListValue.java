package org.soulspace.template.value;

import java.util.Iterator;
import java.util.List;


public interface IListValue extends IValue {

	/**
	 * Create and add a new ListSymbol
	 * @param data
	 */
	void addNewListSymbol(List<IValue> data);

	/**
	 * Create and add a new MapSymbol
	 * @param data
	 */
	void addNewMapSymbol(ISymbolTable data);

	/**
	 * Create and add a new NumericSymbol
	 * @param data
	 */
	void addNewNumericSymbol(String data);

	/**
	 * Create and add a new StringSymbol
	 * @param data
	 */
	void addNewStringSymbol(String data);

	/**
	 * Add a symbol
	 * @param symbol
	 */
	void addSymbol(IValue symbol);

	/**
	 * Returns the list
	 * @return List
	 */
	List<IValue> getData();

	/**
	 * Returns the current entry
	 * @return ISymbol
	 */
	IValue getEntry();

	/**
	 * Returns the current index
	 * @return long
	 */
	int getIndex();

	int size();

	/**
	 * Returns an Iterator for this list symbol
	 * @return
	 */
	Iterator<IValue> iterateData();

	/**
	 * Checks for other entries.
	 * @return
	 */
	boolean hasNext();

	/**
	 * Returns the next entry
	 * @return
	 */
	Object next();

	/**
	 * Sets the entry.
	 * @param entry The entry to set
	 */
	void setEntry(IValue entry);

	/**
	 * Sets the index.
	 * @param index The index to set
	 */
	void setIndex(int index);

	void setData(List<IValue> list);

}