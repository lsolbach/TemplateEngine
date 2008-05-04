/*
 * Created on Feb 21, 2003
 *
 */
package org.soulspace.template.symbols;

import java.util.List;

/**
 * Interface for symbol tables.
 * 
 * @author soulman
 * 
 */
public interface ISymbolTable {
	
	/**
   * Create and add a new StringSymbol to the symbol table
	 * @param name Name of the new symbol
	 * @param data Data to store in this Symbol
	 */
	public void addNewStringSymbol(String name, String data);

	/**
   * Create and add a new NumericSymbol to the symbol table
	 * @param name Name of the new symbol
	 * @param data Data to store in this Symbol
	 */
	public void addNewNumericSymbol(String name, String data);

	/**
   * Create and add a new ListSymbol to the symbol table
	 * @param name Name of the new symbol
	 * @param data Data to store in this Symbol
	 */
	public void addNewListSymbol(String name, List<ISymbol> data);

	/**
   * Create and add a new MapSymbol to the symbol table
	 * @param name Name of the new symbol
	 * @param data Data to store in this Symbol
	 */
	public void addNewMapSymbol(String name, ISymbolTable data);
		
	/**
   * Add a symbol to the symbol table
	 * @param name Name of the symbol
	 * @param symbol Symbol to add
	 */
  public void addSymbol(String name, ISymbol symbol);

	/**
   * Returns the symbol with this name
	 * @param name Name of the symbol to retrieve
	 * @return
	 */
	public ISymbol getSymbol(String name);
  
	/**
   * Returns the number of symbols in this symbol table
	 * @return Number of symbols
	 */
  public int getSymbolCount();
	
}
