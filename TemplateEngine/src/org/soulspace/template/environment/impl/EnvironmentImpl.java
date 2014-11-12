/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.environment.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.SymbolTableImpl;

public class EnvironmentImpl implements Environment {

	Environment parent;
	SymbolTable symbolTable;
	
	public EnvironmentImpl(SymbolTable symbolTable) {
		this.parent = null;
		this.symbolTable = symbolTable;
	}
	
	public EnvironmentImpl(Environment parent, SymbolTable symbolTable) {
		this.parent = parent;
		this.symbolTable = symbolTable;
	}
	
	public EnvironmentImpl(Environment parent) {
		this.parent = parent;
		this.symbolTable = new SymbolTableImpl();
	}
	
	public Environment getParent() {
		return parent;
	}

	public void setParent(Environment parent) {
		this.parent = parent;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public Value lookupValue(String name) {
		Value result = symbolTable.getSymbol(name);
		if(result != null) {
			return result;
		} else if(parent != null && parent != this && parent.getSymbolTable() != symbolTable) {
			return parent.lookupValue(name);
		}
		return null;
	}
	
	public Value lookupValueInEnvironment(String name) {
		return symbolTable.getSymbol(name);		
	}
	
	public void addValue(String name, Value value) {
		symbolTable.addSymbol(name, value);
	}
	
	public String toString() {
		return printEnvironment();
	}
	
	public String printEnvironment() {
		StringBuilder sb = new StringBuilder(32);		
		if(parent != null) {
			sb.append(parent.printEnvironment());
		}
		sb.append(symbolTable.toString() + "\n");
		return sb.toString();
	}
}
