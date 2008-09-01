package org.soulspace.template.parser.ast;

import java.util.List;

import org.soulspace.template.value.impl.ValueType;

public interface ISignature {

	String getMethodName();
	ValueType getReturnType();
	List<ValueType> getParameterTypes();
	
}
