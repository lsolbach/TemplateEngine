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
