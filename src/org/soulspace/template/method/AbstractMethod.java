package org.soulspace.template.method;

import java.util.Collections;
import java.util.List;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.symbols.ISymbol;

public abstract class AbstractMethod implements IMethod {

	protected String name;
	protected List<Class<? extends ISymbol>> definedTypes;
	protected List<Class<? extends ISymbol>> argumentTypes;
	protected Class<? extends ISymbol> returnType;
	
	public boolean definedFor(ISymbol symbol) {
		return definedTypes.contains(symbol.getClass());
	}

	public List<Class<? extends ISymbol>> getArgumentTypes() {
		return Collections.unmodifiableList(argumentTypes);
	}

	public List<Class<? extends ISymbol>> getDefinedTypes() {
		return Collections.unmodifiableList(definedTypes);
	}

	public String getName() {
		return name;
	}

	public Class<? extends ISymbol> getReturnType() {
		return returnType;
	}

	public boolean validateTypes(List<ISymbol> arguments) {
		if(arguments.size() != argumentTypes.size() + 1) {
			throw new GenerateException("Argument count mismatch on method " + getName());			
		}
		if(false) {
			// FIXME implement generic type checking
			throw new GenerateException("Type mismatch on method " + getName());
		}
		return true;
	}

	public ISymbol evaluate(List<ISymbol> arguments) {
		validateTypes(arguments);
		return doEvaluation(arguments);
	}
	
	protected abstract ISymbol doEvaluation(List<ISymbol> arguments);
}
