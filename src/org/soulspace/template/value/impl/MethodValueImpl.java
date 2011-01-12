package org.soulspace.template.value.impl;

import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

public class MethodValueImpl implements MethodValue {

	private String methodName = null;

	public MethodValueImpl(String methodName) {
		this.methodName = methodName;
	}
	
	public String evaluate() {
		return "";
	}

	public ValueType getType() {
		return ValueType.METHOD;
	}

	public boolean isTrue() {
		return false;
	}

	public String getData() {
		return methodName;
	}

	public void setData(String methodName) {
		this.methodName = methodName;
	}

}
