/**
 * File: SymbolType.java 
 *
 * Created on Dec 3, 2002
 */
package org.soulspace.template.symbols.impl;

import org.soulspace.template.symbols.ISymbolType;

/**
 * @author soulman
 *
 * Typesafe enumeration of symbol types.
 * Version: $Id
 */
public class SymbolType implements ISymbolType {

  public final static SymbolType STRING   = new SymbolType("STRING");
  public final static SymbolType NUMERIC  = new SymbolType("NUMERIC");
  public final static SymbolType LIST     = new SymbolType("LIST");
  public final static SymbolType MAP      = new SymbolType("HASH");
  public final static SymbolType OBJECT   = new SymbolType("OBJECT");
  public final static SymbolType METHOD   = new SymbolType("METHOD");

	private final String name;
	
	private SymbolType(String name) {
		this.name = name;	
	}

	public String toString() {
		return name;	
	}
	
}
