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