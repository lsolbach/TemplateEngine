/*
 * Created on Feb 21, 2003
 *
 */
package org.soulspace.template.value;

/**
 * All elements to be stored in SymbolTables have to implement ISymbol.
 * 
 * @author soulman
 */
public interface Value {

	/**
	 * Returns the type of this symbol
	 * 
	 * @return Type of the symbol
	 */
	ValueType getType();

	/**
	 * Evaluates the Symbol.
	 * 
	 * @return
	 */
	String evaluate();

	boolean isTrue();
	
	String asString();
	
	long asLong();

}
