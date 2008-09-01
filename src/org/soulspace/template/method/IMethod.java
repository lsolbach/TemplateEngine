package org.soulspace.template.method;

import java.util.List;

import org.soulspace.template.value.IValue;

public interface IMethod {

	String getName();
	List<Class<? extends IValue>> getArgumentTypes();
	Class<? extends IValue> getReturnType();
	List<Class<? extends IValue>> getDefinedTypes();	
	boolean definedFor(IValue symbol);
	IValue evaluate(List<IValue> arguments);

}
