package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IMethodNode;
import org.soulspace.template.parser.ast.ISignature;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.ValueType;

public class MethodNode extends AbstractAstNode implements IMethodNode {

//	private String methodName;
	private ValueType returnType;
	private IMethodNode superMethod = null;
	private ISignature signature = null;
	// stack to store context in recursive calls
	private Stack<MethodContext> callStack = new Stack<MethodContext>();
	private MethodContext context = null;

	public MethodNode() {
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
	
	public IMethodNode getSuperMethod() {
		return superMethod;
	}
	
	public void setSuperMethod(IMethodNode superMethod) {
		this.superMethod = superMethod;
	}
	
	public String getMethodName() {
		return getData();
	}
	
	public ISignature getSignature() {
		if(signature == null) {
			signature = new SignatureImpl(getMethodName(), getReturnType(), getParameterTypes());
		}
		return signature;
	}
	
	public String getSignatureString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getMethodName());
		sb.append("(");
		// TODO add param type string, if type inference works in method call
		sb.append(((ParamListNode) getChild(0)).getChildCount());
		sb.append(")");
		return sb.toString();
	}
	
	public List<ValueType> getParameterTypes() {
		List<ValueType> list = new ArrayList<ValueType>();

		ParamListNode paramList = (ParamListNode) getChild(0);
		for(IAstNode node : paramList.getChildNodes()) {
			DeclarationNode param = (DeclarationNode) node;
			list.add(ValueType.valueOf(param.getData()));
		}
		return list;
	}
	
	public String getParameterTypeString() {
		StringBuilder sb = new StringBuilder();
		boolean firstParam = true;
		ParamListNode paramList = (ParamListNode) getChild(0);
		for(IAstNode node : paramList.getChildNodes()) {
			DeclarationNode param = (DeclarationNode) node;
			if(!firstParam) {
				sb.append(",");
			} else {
				firstParam = false;
			}
			sb.append(param.getData());
		}
		return sb.toString();
	}
	
	public IValue callSuperMethod() {
		return getSuperMethod().generateSymbol(this, getSymbolTable());
	}
	
	public IValue generateSymbol(IAstNode returnNode, ISymbolTable symbolTable) throws GenerateException {
		IValue result = new StringValue("");
		if(context != null) {
	    callStack.push(context);
		}
    context = new MethodContext(returnNode, symbolTable);
    setSymbolTable(symbolTable);

    IAstNode node = null;
		if((node = getChild(1)) != null && node.getType().equals(AstNodeType.TERM)) {
			result = node.generateSymbol();
		}

		if(!callStack.empty()) {
			context = callStack.pop();			
	    //setParent(context.getReturnNode());
	    setSymbolTable(context.getSymbolTable());
		}
		
		if(result != null && !result.getType().equals(returnType)) {
			if(returnType.equals(ValueType.STRING)) {
				result = asString(result);
			} else if (returnType.equals(ValueType.NUMERIC)) {
				result = asNumeric(result);
			} else {
				throw new GenerateException("Return type of method " + getData()
						+ " is " + returnType + ", but the result is of type "
						+ result.getType().getName() + ". The value of the result: " + result.evaluate());
			}
		}
		return result;
	}
		
	/* (non-Javadoc)
	 * @see org.soulspace.template.parser.ast.impl.AstNode#getParent()
	 */
	@Override
	public IAstNode getParent() {
		// FIXME endless loop in getMethodTable()
		if(context.getReturnNode() != null) {
			return context.getReturnNode();			
		} else {
			return super.getParent();
		}
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.parser.ast.impl.AstNode#getSymbolTable()
	 */
	@Override
	public ISymbolTable getSymbolTable() {
		if(context.getSymbolTable() != null) {
			return context.getSymbolTable();			
		} else {
			return super.getSymbolTable();
		}
	}

	public IValue generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on MethodNode");
	}

	/**
	 * Method Context for recursive method calls.
	 * @author soulman
	 */
	private class MethodContext {
		ISymbolTable symbolTable;
		IAstNode returnNode;
		
		public MethodContext() {
		}
		
		public MethodContext(IAstNode returnNode, ISymbolTable symbolTable) {
			this.returnNode = returnNode;
			this.symbolTable = symbolTable;
		}

		/**
		 * @return the symbolTable
		 */
		public ISymbolTable getSymbolTable() {
			return symbolTable;
		}

		/**
		 * @return the returnNode
		 */
		public IAstNode getReturnNode() {
			return returnNode;
		}
	}
	
	private ISignature buildSignature() {
		ISignature sig = new SignatureImpl(getMethodName(), getReturnType(), getParameterTypes());
		return sig;
	}
}