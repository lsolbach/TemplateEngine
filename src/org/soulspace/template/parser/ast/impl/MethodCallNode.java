package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IMethodNode;
import org.soulspace.template.parser.ast.ISignature;
import org.soulspace.template.value.ISymbolTable;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.SymbolTable;

public class MethodCallNode extends AbstractAstNode {

	public MethodCallNode() {
		super();
		setType(AstNodeType.METHOD_CALL);
	}
	
	public String getSignatureString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getData());
		sb.append("(");
		// TODO add argument types
		IAstNode argList = getChild(0);
		sb.append(argList.getChildCount());
		sb.append(")");
		return sb.toString();
	}
	
	public ISignature getSignature() {
		
		return null;
	}
	
	public String getMethodName() {
		return getData();
	}
	
	public IValue generateSymbol() {
		IMethodNode methodNode;

		if(getMethodName().equals("super")) {
			IAstNode node = getParent();
			boolean found = false;
			while(!found) {
				if(node != null) {
					if(node instanceof MethodNode) {
						MethodNode mNode = (MethodNode) node;
						System.out.println("method " + mNode.getMethodName());
						if(mNode.getSuperMethod() == null) {
							throw new GenerateException("super() call, but there is no super method");							
						}
						return mNode.callSuperMethod();
					}
					node = node.getParent();
				} else {
					throw new GenerateException("super() call outside a method body");
				}
			}
		} else {
			// FIXME use signature instead of methodName for lookup
			if((methodNode = getMethodNode(getSignatureString())) != null) {
				// create symbol table for arguments
				ISymbolTable symbolTable = createSymbolTable(methodNode);
		    
				// generate from the method
				return ((MethodNode) methodNode).generateSymbol(methodNode, symbolTable);
			} else {
				throw new GenerateException("No method node found for signature " + getSignatureString());
			}
		}
		return new StringValue("");
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 * @throws GenerateException
	 */
	ISymbolTable createSymbolTable(IAstNode node) throws GenerateException {
		ISymbolTable symbolTable = new SymbolTable();
		
		if(!(node instanceof MethodNode)) {
			throw new GenerateException("Wrong node type");
		}
		
		IAstNode paramList = node.getChild(0);
		IAstNode argList = getChild(0);
		IAstNode paramNode;
		IAstNode argNode;
		String paramName;
		String paramType;
		String argData;
		
		if(paramList == null) {
			throw new GenerateException("Missing parameter list");
		}
		
		if(!(paramList instanceof ParamListNode)) {
			// check if possible and feasible
			return symbolTable;
		}

		if(paramList.getChildCount() != argList.getChildCount()) {
			throw new GenerateException("Wrong argument count in method " + getData());			
		}
		
		for(int i = 0; i < paramList.getChildCount(); i++) {
			paramNode = paramList.getChild(i);
      paramType = paramNode.getData();
      paramName = paramNode.getChild(0).getData();
      argNode = argList.getChild(i);
      argData = argNode.getData();
      
      IValue symbol = null;
    	// FIXME refactor the whole thing just to argNode.generateSymbol() with type checking
    	symbolTable.addSymbol(paramName, argNode.generateSymbol());
//      if(argNode instanceof IdentifierNode) {
//      	symbol = argNode.lookupSymbol(argData);
//      	if(symbol == null) {
//      		System.out.println("method call: symbol for arg " + argData + " not found.");      		
//      	}
//      	symbolTable.addSymbol(paramName, symbol);
//      } else if(argNode instanceof DereferenceNode) {
//      	symbol = argNode.generateSymbol();
//      	if(symbol == null) {
//      		System.out.println("method call: symbol for arg " + argData + " not found.");      		
//      	}
//      	symbolTable.addSymbol(paramName, symbol);
//      } else if(argNode instanceof StringConstNode) {
//      	symbolTable.addNewStringSymbol(paramName, argData);
//      } else if(argNode instanceof NumericConstNode) {
//      	symbolTable.addNewNumericSymbol(paramName, argData);
//      } else {
//      	symbolTable.addSymbol(paramName, argNode.generateSymbol());
//      }      
    }
    
		return symbolTable;
	}
	
	void addSymbol(ISymbolTable symbolTable, String data) {
		
	}

}
