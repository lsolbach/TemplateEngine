package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.MethodNode;
import org.soulspace.template.parser.ast.Signature;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;
import org.soulspace.template.value.impl.StringValueImpl;

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

	public Value callSuperMethod() {
		return getSuperMethod().generateSymbol(this, getSymbolTable());
	}

	public Value generateSymbol(AstNode returnNode, SymbolTable symbolTable)
			throws GenerateException {
		// this given symbolTable contains the arguments of the call
		// at the moment, these are set to this node
		// and chained with the dynamic scope, that is the chain of
		// parent symbol tables via the caller
		// TODO add capability of lexical scoping to enable closures
		// TODO reference scope of the parent of the method node and
		// TODO not of the caller, if method node parent is not the term node
		// TODO that is the direct child of the root node.
		// TODO Which means, that the method node was defined in a block that
		// TODO could function as the lexical scope of the method
		
		Value result = null;
		if (context != null) {
			callStack.push(context);
		}
		context = new MethodContext(returnNode, symbolTable);
		setSymbolTable(symbolTable);

		AstNode node = null;
		if ((node = getChild(1)) != null
				&& node.getType().equals(AstNodeType.TERM)) {
			result = ((TermNodeImpl) node).generateSymbol(returnType);
		}

		if (!callStack.empty()) {
			context = callStack.pop();
			// setParent(context.getReturnNode());
			setSymbolTable(context.getSymbolTable());
		}

		
		if (result != null) {
			validateResultType(returnType, result.getType());
			if (returnType.equals(ValueType.STRING)) {
				result = asString(result);
			} else if (returnType.equals(ValueType.NUMERIC)) {
				result = asNumeric(result);
			}
		}
		return result;
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.parser.ast.impl.AstNode#getParent()
	 */
	@Override
	public AstNode getParent() {
		// FIXME endless loop in getMethodTable()
		if (context.getReturnNode() != null) {
			return context.getReturnNode();
		} else {
			return super.getParent();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.parser.ast.impl.AstNode#getSymbolTable()
	 */
	@Override
	public SymbolTable getSymbolTable() {
		if (context.getSymbolTable() != null) {
			return context.getSymbolTable();
		} else {
			return super.getSymbolTable();
		}
	}

	public Value generateValue() {
		throw new GenerateException(
				"Method generateSymbol() must not be called on MethodNodeImpl! Template "
				+ getTemplate() + ", line " + getLine());
	}

	private Signature buildSignature() {
		Signature sig = new SignatureImpl(getMethodName(), getReturnType(),
				getParameterTypes());
		return sig;
	}

	/**
	 * Method Context for recursive method calls.
	 * 
	 * @author soulman
	 */
	private class MethodContext {
		SymbolTable symbolTable;
		AstNode returnNode;

		public MethodContext() {
		}

		public MethodContext(AstNode returnNode, SymbolTable symbolTable) {
			this.returnNode = returnNode;
			this.symbolTable = symbolTable;
		}

		/**
		 * @return the symbolTable
		 */
		public SymbolTable getSymbolTable() {
			return symbolTable;
		}

		/**
		 * @return the returnNode
		 */
		public AstNode getReturnNode() {
			return returnNode;
		}
	}

}