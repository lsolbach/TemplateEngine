/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.ArrayList;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolTable;

public class DeclarationNode extends AstNode {
  
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

	public ISymbol generateSymbol() {
    IAstNode child = null;
    ISymbol symbol = null;
    String name = "";
    String type = getData();
    
    if((child = getChild(0)) != null) {
      name = child.getData();
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
      getSymbolTable().addNewListSymbol(name, new ArrayList<ISymbol>());
    } else if (type.equals("map")) {
      getSymbolTable().addNewMapSymbol(name, new SymbolTable());
    }

    // TODO is returning of the created symbol useful?
    // return symbol = lookupSymbolInBlock(name);
		return new StringSymbol("");
	}

}
