package org.soulspace.template.method;

import java.util.List;

import org.soulspace.template.symbols.ISymbol;

public interface IMethod {

	String getName();
	List<Class<? extends ISymbol>> getArgumentTypes();
	Class<? extends ISymbol> getReturnType();
	List<Class<? extends ISymbol>> getDefinedTypes();	
	boolean definedFor(ISymbol symbol);
	ISymbol evaluate(List<ISymbol> arguments);

}
