package org.soulspace.template.parser.ast.impl;

import java.util.Stack;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.StringSymbol;

public class MethodNode extends AstNode {

	String methodName;
	// stack to store context in recursive calls
	Stack<MethodContext> callStack = new Stack<MethodContext>();
	MethodContext context = null;

	public MethodNode() {
		setType(AstNodeType.METHOD);
		context = new MethodContext();
	}
		
	public ISymbol generateSymbol(IAstNode returnNode, ISymbolTable symbolTable) throws GenerateException {
		ISymbol result = new StringSymbol("");
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

	public ISymbol generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on MethodNode");
	}
}