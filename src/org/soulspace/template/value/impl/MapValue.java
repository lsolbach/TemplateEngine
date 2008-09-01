/*
 * Created on Feb 14, 2003
 *
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;


/**
 * Map Symbol, stores SymbolTables.
 * 
 * @author soulman
 */
public class MapValue implements IValue, IMapValue {
	
	private ISymbolTable data; 

	/**
	 * Constructor
	 */
	public MapValue() {
		this.data = new SymbolTable();
	}

  /**
   * Constructor
   * @param symbolTable
   */
	public MapValue(ISymbolTable symbolTable) {
		this.data = symbolTable;
	}

  /* (non-Javadoc)
   * @see org.soulspace.templates.AbstractSymbol#getType()
   */
  public ValueType getType() {
    return ValueType.MAP;
  }

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IMapType#getData()
	 */
	public ISymbolTable getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.symbols.impl.IMapType#setData(org.soulspace.template.symbols.ISymbolTable)
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
