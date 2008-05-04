/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;
import java.util.List;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.MapSymbol;


public class IdentifierNode extends ExpressionNode implements IExpressionNode {

  public IdentifierNode() {
    this(null);
  }
  
  public IdentifierNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.IDENTIFIER);
  }

	public ISymbol generateSymbol() {
    ISymbol symbol = null;
    
    symbol = lookupSymbol(getData());
    if(symbol == null) {
    	throw new GenerateException("Variable " + getData() + " not found");
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
  ISymbol lookup(ISymbol symbol) {
    ISymbol aSymbol = symbol;
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
  ISymbol derefSymbol(ISymbol symbol, String name) {
  	ISymbol aSymbol = null;
    if (symbol == null) {
    	aSymbol = lookupSymbol(name);
    } else if (symbol instanceof MapSymbol) {
    	aSymbol = ((MapSymbol) symbol).getData().getSymbol(name);
    } else if (symbol instanceof ListSymbol) {
      if(isNumeric(name)) {
      	// TODO necessary?
        // Get entry by index
        List<ISymbol> list = ((ListSymbol) symbol).getData();
        int i = Integer.parseInt(roundResult(name));
        if(list.size() > i) {
        	aSymbol = list.get(i);
        }
      }
    }

    return aSymbol;
  }

}
