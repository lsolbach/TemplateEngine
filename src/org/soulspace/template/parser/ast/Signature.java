package org.soulspace.template.parser.ast;

import java.util.List;

import org.soulspace.template.value.ValueType;

public interface Signature {

	String getMethodName();
	ValueType getReturnType();
	List<ValueType> getParameterTypes();
	
}
