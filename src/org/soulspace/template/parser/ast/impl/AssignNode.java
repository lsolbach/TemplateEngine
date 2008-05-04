/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.MapSymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolType;

public class AssignNode extends ExpressionNode implements IExpressionNode {
  
  /**
   * 
   */
  public AssignNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public AssignNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ASSIGN);
  }


	public ISymbol generateSymbol() {
    IAstNode child = null;
    ISymbol symbol = null;
    String name = "";

    if((child = getChild(0)) != null && child instanceof IdentifierNode) {
      name = child.getData();
      symbol = lookupSymbol(name);
    } else {
      throw new GenerateException("Expecting Identifier");
    }
    
    if(symbol == null) {
      throw new GenerateException("Symbol not found: " + name);
    }
    
    if((child = getChild(1)) != null) {
      if (symbol.getType().equals(SymbolType.STRING)) {
      	// assign to string symbol
      	ISymbol rSymbol = ((IExpressionNode) child).generateSymbol();
        ((StringSymbol) symbol).setData(((StringSymbol) rSymbol).getData());
      } else if (symbol.getType().equals(SymbolType.NUMERIC)) {
      	// assign to numeric symbol
      	ISymbol rSymbol = ((IExpressionNode) child).generateSymbol();
        ((NumericSymbol) symbol).setData(((NumericSymbol) rSymbol).getData());
      } else if (symbol.getType().equals(SymbolType.LIST)) {
      	// assign to list symbol
      	ISymbol aSymbol = ((IExpressionNode) child).generateSymbol();
        if (!(aSymbol instanceof ListSymbol)) {
          throw new GenerateException("Symbol not of type list "
              + child.getData());
        }
        ((ListSymbol) symbol).setData(((ListSymbol) aSymbol).getData());
      } else if (symbol.getType().equals(SymbolType.MAP)) {
      	// assign to map symbol
      	ISymbol aSymbol = ((IExpressionNode) child).generateSymbol();
        if (!(aSymbol instanceof MapSymbol)) {
          throw new GenerateException("Symbol not of type map: "
              + child.getData());
        }
        ((MapSymbol) symbol).setData(((MapSymbol) aSymbol).getData());
      }
    } else {
      throw new GenerateException("Expecting something to assign");
    }    
    		
		return new StringSymbol("");
	}
  
}
