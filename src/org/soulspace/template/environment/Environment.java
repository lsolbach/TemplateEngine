/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.environment;

import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;

public interface Environment {

	Environment getParent();
	void setParent(Environment parent);
	
	SymbolTable getSymbolTable();
	void setSymbolTable(SymbolTable symbolTable);
	
	Value lookupValue(String name);
	Value lookupValueInEnvironment(String name);
	void addValue(String name, Value value);
	
	String printEnvironment();
}
