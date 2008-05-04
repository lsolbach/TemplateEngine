package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolTable;

public class MethodCallNode extends AstNode {

	public MethodCallNode() {
		super();
		setType(AstNodeType.METHOD_CALL);
	}
	
	public ISymbol generateSymbol() {
		IAstNode methodNode;
		String methodName = getData();
		
		if((methodNode = getMethodNode(methodName)) != null) {
			// create symbol table for arguments
			ISymbolTable symbolTable = createSymbolTable(methodNode);
	    
			// generate from the method
			return ((MethodNode) methodNode).generateSymbol(methodNode, symbolTable);
		}
		return new StringSymbol("");
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
      
      ISymbol symbol = null;
    	// FIXME refactor the whole thing just to argNode.generateSymbol() with type checking
      if(argNode instanceof IdentifierNode) {
      	symbol = argNode.lookupSymbol(argData);
      	if(symbol == null) {
      		System.out.println("method call: symbol for arg " + argData + " not found.");      		
      	}
      	symbolTable.addSymbol(paramName, symbol);
      } else if(argNode instanceof DereferenceNode) {
      	symbol = argNode.generateSymbol();
      	if(symbol == null) {
      		System.out.println("method call: symbol for arg " + argData + " not found.");      		
      	}
      	symbolTable.addSymbol(paramName, symbol);
      } else if(argNode instanceof StringConstNode) {
      	symbolTable.addNewStringSymbol(paramName, argData);
      } else if(argNode instanceof NumericConstNode) {
      	symbolTable.addNewNumericSymbol(paramName, argData);
      } else {
      	symbolTable.addSymbol(paramName, argNode.generateSymbol());
//      	if(paramType.equals("numeric")) {
//      		symbolTable.addSymbol(paramName, argNode.generateSymbol());
//      		symbolTable.addNewNumericSymbol(paramName, argNode.generate());
//      	} else if(paramType.equals("string")) {
//        	symbolTable.addNewStringSymbol(paramName, argNode.generate());      		
//      	}
      }      
    }
    
		return symbolTable;
	}
	
	void addSymbol(ISymbolTable symbolTable, String data) {
		
	}

}
