/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.method.impl.SignatureImpl;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.parser.ast.Signature;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.SymbolTableImpl;

public class MethodNodeImpl extends AbstractAstNode implements MethodNode {

	// private String methodName;
	private ValueType returnType;
	private MethodNode superMethod = null;
	private Signature signature = null;
	// stack to store context in recursive calls
	private Stack<MethodContext> callStack = new Stack<MethodContext>();
	private MethodContext context = null;

	public MethodNodeImpl() {
		setType(AstNodeType.METHOD);
		context = new MethodContext();
	}

	public void setReturnType(String returnType) {
		this.returnType = ValueType.valueOf(returnType);
	}

	public void setReturnType(ValueType returnType) {
		this.returnType = returnType;
	}

	public ValueType getReturnType() {
		return returnType;
	}

	public MethodNode getSuperMethod() {
		return superMethod;
	}

	public void setSuperMethod(MethodNode superMethod) {
		this.superMethod = superMethod;
	}

	public String getMethodName() {
		return getData();
	}

	public Signature getSignature() {
		if (signature == null) {
			signature = new SignatureImpl(getMethodName(), getReturnType(),
					getParameterTypes());
		}
		return signature;
	}

	public String getSignatureString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getMethodName());
		sb.append("(");
		sb.append(getParameterTypeString());
		sb.append(")");
		return sb.toString();
	}

	public List<ValueType> getParameterTypes() {
		List<ValueType> list = new ArrayList<ValueType>();

		ParamListNodeImpl paramList = (ParamListNodeImpl) getChild(0);
		for (AstNode node : paramList.getChildNodes()) {
			DeclarationNodeImpl param = (DeclarationNodeImpl) node;
			list.add(ValueType.valueOf(param.getData()));
		}
		return list;
	}

	public String getParameterTypeString() {
		StringBuilder sb = new StringBuilder();
		boolean firstParam = true;
		ParamListNodeImpl paramList = (ParamListNodeImpl) getChild(0);
		for (AstNode node : paramList.getChildNodes()) {
			DeclarationNodeImpl param = (DeclarationNodeImpl) node;
			if (!firstParam) {
				sb.append(",");
			} else {
				firstParam = false;
			}
			sb.append(param.getData());
		}
		return sb.toString();
	}

	public Value callSuperMethod(List<Value> valueList) {
		return getSuperMethod().generateSymbol(
				getEnvironment(), this, valueList);
	}

	public SymbolTable createSymbolTable(List<Value> valueList) {
		SymbolTable symbolTable = new SymbolTableImpl();

		if (getChild(0) == null) {
			throw new GenerateException("Missing parameter list! Template " + getTemplate() + ", line " + getLine());
		}
		AstNode paramList = getChild(0);
		if (paramList.getChildCount() != valueList.size()) {
			throw new GenerateException("Wrong argument count in method "
					+ getData() + "! Template " + getTemplate() + ", line " + getLine());
		}

		for (int i = 0; i < paramList.getChildCount(); i++) {
			AstNode paramNode = paramList.getChild(i);
			String paramName = paramNode.getChild(0).getData();
			symbolTable.addSymbol(paramName, valueList.get(i));
		}

		return symbolTable;
	}

	void validateResultType(ValueType returnType, ValueType resultType) {
		if(!(returnType.equals(resultType)
				|| returnType.equals(ValueType.STRING)
				|| returnType.equals(ValueType.NUMERIC)
				|| returnType.equals(ValueType.ANY))) {
			throw new GenerateException("Return type of method "
					+ getData() + " is " + returnType
					+ ", but the result is of type "
					+ resultType.getName()
					+ "! Template " + getTemplate() + ", line " + getLine());
		}
	}
	
	@Override
	public AstNode getParent() {
		if (context.getReturnNode() != null) {
			return context.getReturnNode();
		} else {
			return super.getParent();
		}
	}

	@Override
	public Environment getEnvironment() {
		if (context.getEnvironment() != null) {
			return context.getEnvironment();
		} else {
			return super.getEnvironment();
		}
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		throw new GenerateException(
				"Method generateSymbol() must not be called on MethodNodeImpl! Template "
				+ getTemplate() + ", line " + getLine());
	}

	public Value generateSymbol(Environment environment, AstNode returnNode,
			List<Value> valueList) {
		// FIXME use Environment
		// this given list of values contains the arguments of the call
		// at the moment, these are set to this node
		// and chained with the dynamic scope, that is the chain of
		// parent symbol tables via the caller

		Value result = null;
		if (context != null) {
			callStack.push(context);
		}

		SymbolTable paramTable = createSymbolTable(valueList);
		environment = new EnvironmentImpl(environment, paramTable);
		setEnvironment(environment);		
		context = new MethodContext(environment, returnNode);
//		System.out.println("Method execution " + getData());
//		System.out.println(environment.printEnvironment());


		AstNode node = null;
		if ((node = getChild(1)) != null
				&& node.getType().equals(AstNodeType.TERM)) {
			result = ((TermNodeImpl) node).generateSymbol(environment, returnType);
		}
		
		if (result != null) {
			validateResultType(returnType, result.getType());
			if(returnType.equals(ValueType.METHOD)
					&& result.getType().equals(ValueType.METHOD)) {
			} else if (returnType.equals(ValueType.STRING)) {
				result = asString(result);
			} else if (returnType.equals(ValueType.NUMERIC)) {
				result = asNumeric(result);
			}
		}
		
		if (!callStack.empty()) {
			context = callStack.pop();
			setEnvironment(context.getEnvironment());
		}

		return result;
	}

	/**
	 * Method Context for recursive method calls.
	 * 
	 * @author soulman
	 */
	private class MethodContext {
		Environment environment;
		AstNode returnNode;

		public MethodContext() {
		}

		public MethodContext(Environment environment, AstNode returnNode) {
			this.environment = environment;
			this.returnNode = returnNode;
		}

		public Environment getEnvironment() {
			return environment;
		}

		/**
		 * @return the returnNode
		 */
		public AstNode getReturnNode() {
			return returnNode;
		}
	}

}