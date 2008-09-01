package org.soulspace.template.value;


public interface IMapValue extends IValue {

	/**
	 * Returns the symbol table
	 * @return SymbolTable
	 */
	ISymbolTable getData();

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	void setData(ISymbolTable data);

}