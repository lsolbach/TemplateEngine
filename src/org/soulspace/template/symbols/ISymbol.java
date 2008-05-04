/*
 * Created on Feb 21, 2003
 *
 */
package org.soulspace.template.symbols;



/**
 * All elements to be stored in SymbolTables have to implement ISymbol.
 * 
 * @author soulman
 */
public interface ISymbol {

	/**
	 * Returns the type of this symbol
	 * @return Type of the symbol
	 */
	ISymbolType getType();

  /**
   * Evaluates the Symbol.
   * @return
   */
  String evaluate();

  boolean isTrue();
}
