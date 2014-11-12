/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.value.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.ValueType;

public class MethodValueImpl implements MethodValue {

	private String methodName = null;
	private MethodNode methodNode = null;
	private Environment environment = null;
	
	public MethodValueImpl(String methodName) {
		this.methodName = methodName;
	}
	
	public MethodValueImpl(MethodNode methodNode) {
		this.methodNode = methodNode;
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

	public MethodNode getMethodNode() {
		return methodNode;
	}

	public void setMethodNode(MethodNode methodNode) {
		this.methodNode = methodNode;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
//		System.out.println("Setting environment to method " + methodName + " " + toString());
//		System.out.println(environment.printEnvironment());
		this.environment = environment;
	}
	
	public String asString() {
		return getData();
	}

	public long asLong() {
		return Long.valueOf(getData().length());
	}

}
