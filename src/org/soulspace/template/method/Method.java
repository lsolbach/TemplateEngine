package org.soulspace.template.method;

import java.util.List;

import org.soulspace.template.value.Value;

public interface Method {

	String getName();
	List<Class<? extends Value>> getArgumentTypes();
	Class<? extends Value> getReturnType();
	List<Class<? extends Value>> getDefinedTypes();	
	boolean definedFor(Value symbol);
	Value evaluate(List<Value> arguments);

}
