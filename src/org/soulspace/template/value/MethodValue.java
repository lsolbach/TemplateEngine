package org.soulspace.template.value;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.MethodNode;

public interface MethodValue extends Value {

	String getData();
	
	void setData(String methodName);
	
	MethodNode getMethodNode();
	
	void setMethodNode(MethodNode node);
	
	Environment getEnvironment();
	
	void setEnvironment(Environment environment);
}
