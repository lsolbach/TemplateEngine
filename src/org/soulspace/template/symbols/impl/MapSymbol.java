/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.symbols.impl;

import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;


/**
 * Map Symbol, stores SymbolTables.
 * 
 * @author soulman
 */
public class MapSymbol implements ISymbol {
	
	private ISymbolTable data; 

	/**
	 * Constructor
	 */
	MapSymbol() {
		this.data = new SymbolTable();
	}

  /**
   * Constructor
   * @param symbolTable
   */
	MapSymbol(ISymbolTable symbolTable) {
		this.data = symbolTable;
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public SymbolType getType() {
    return SymbolType.MAP;
  }

	/**
   * Returns the symbol table
	 * @return SymbolTable
	 */
	public ISymbolTable getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data The data to set
	 */
	public void setData(ISymbolTable data) {
		this.data = data;
	}

  /**
   * 
   */
  public String evaluate() {
    String result = String.valueOf(getData().getSymbolCount());

    return result;
  }

	public boolean isTrue() {
		return data.getSymbolCount() != 0;
	}

}
