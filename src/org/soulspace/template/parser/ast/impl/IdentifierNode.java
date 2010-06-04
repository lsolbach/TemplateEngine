/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;
import java.util.List;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.IValue;


public class IdentifierNode extends AbstractAstNode {

  public IdentifierNode() {
    this(null);
  }
  
  public IdentifierNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.IDENTIFIER);
  }

	public IValue generateSymbol() {
    IValue symbol = null;
    
    symbol = lookupSymbol(getData());
    if(symbol == null) {
    	throw new GenerateException("Variable " + getData() + " not found! Template " + getTemplate() + ", line " + getLine());
    }

    // evaluate indexed access (a[b])
    symbol = lookup(symbol);

    return symbol;
	}
  
  /**
   * 
   * @param symbol
   * @return
   */
  IValue lookup(IValue symbol) {
    IValue aSymbol = symbol;
    IAstNode child = null;
    try {
      Iterator<IAstNode> it = getChildNodes().iterator();
      while(it.hasNext() && aSymbol != null) {
        child = it.next();
        // TODO implement direct lookup without use of deref
        aSymbol = derefSymbol(aSymbol, child.generateSymbol().evaluate());
        if(aSymbol == null) {
          // System.out.println("Warning: Lookup for " + child.getData() + " failed");
        }
      }
    } catch(GenerateException e) {
      System.out.println(e.getMessage());
    }
    
    return aSymbol;
  }

  /**
   * @param symbolTable
   * @param name
   * @return AbstractSymbol
   */
  IValue derefSymbol(IValue symbol, String name) {
  	IValue aSymbol = null;
    if (symbol == null) {
    	aSymbol = lookupSymbol(name);
    } else if (symbol instanceof IMapValue) {
    	aSymbol = ((IMapValue) symbol).getData().getSymbol(name);
    } else if (symbol instanceof IListValue) {
      if(isNumeric(name)) {
      	// TODO necessary?
        // Get entry by index
        List<IValue> list = ((IListValue) symbol).getData();
        int i = Integer.parseInt(roundResult(name));
        if(list.size() > i) {
        	aSymbol = list.get(i);
        }
      }
    }

    return aSymbol;
  }

}
