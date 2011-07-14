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
