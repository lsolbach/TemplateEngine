package org.soulspace.template.method;

import java.util.Collections;
import java.util.List;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.value.IValue;

public abstract class AbstractMethod implements IMethod {

	protected String name;
	protected List<Class<? extends IValue>> definedTypes;
	protected List<Class<? extends IValue>> argumentTypes;
	protected Class<? extends IValue> returnType;
	
	public boolean definedFor(IValue symbol) {
		return definedTypes.contains(symbol.getClass());
	}

	public List<Class<? extends IValue>> getArgumentTypes() {
		return Collections.unmodifiableList(argumentTypes);
	}

	public List<Class<? extends IValue>> getDefinedTypes() {
		return Collections.unmodifiableList(definedTypes);
	}

	public String getName() {
		return name;
	}

	public Class<? extends IValue> getReturnType() {
		return returnType;
	}

	public boolean validateTypes(List<IValue> arguments) {
		if(arguments.size() != argumentTypes.size() + 1) {
			throw new GenerateException("Argument count mismatch on method " + getName());			
		}
		if(false) {
			// FIXME implement generic type checking
			throw new GenerateException("Type mismatch on method " + getName());
		}
		return true;
	}

	public IValue evaluate(List<IValue> arguments) {
		validateTypes(arguments);
		return doEvaluation(arguments);
	}
	
	protected abstract IValue doEvaluation(List<IValue> arguments);
}
