/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;
import org.soulspace.template.value.impl.SymbolTable;

public class DeclarationNode extends AbstractAstNode {
  
  /**
   * 
   */
  public DeclarationNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public DeclarationNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.DECLARATION);
  }

	public IValue generateSymbol() {
    IAstNode child = null;
    IValue symbol = null;
    String name = "";
    String type = getData();
    
    if((child = getChild(0)) == null) {
    	throw new GenerateException("No identifier given for declaration");
    }
  	if(child instanceof IdentifierNode) {
    	name = child.getData();
      symbol = lookupSymbolInBlock(name);    		
  	} else if(child instanceof AssignNode) {
  		name = child.getChild(0).getData();
      symbol = lookupSymbolInBlock(name);    		
  	}
    
    if(symbol != null) {
      throw new GenerateException("Symbol already exists: " + name);
    }
    
    if (type.equals("string")) {
      getSymbolTable().addNewStringSymbol(name, "");
    } else if (type.equals("numeric")) {
      getSymbolTable().addNewNumericSymbol(name, "0");
    } else if (type.equals("list")) {
      getSymbolTable().addNewListSymbol(name, new ArrayList<IValue>());
    } else if (type.equals("map")) {
      getSymbolTable().addNewMapSymbol(name, new SymbolTable());
    }
    if(child instanceof AssignNode) {
    	// evaluate assign expression
    	child.generateSymbol();
    }
    
    // TODO is returning of the created symbol useful?
    // return symbol = lookupSymbolInBlock(name);
		return new StringValue("");
	}

}
