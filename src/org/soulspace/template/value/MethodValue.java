package org.soulspace.template.value;

import org.soulspace.template.parser.ast.MethodNode;

public interface MethodValue extends Value {

	String getData();
	
	void setData(String methodName);
	
}
