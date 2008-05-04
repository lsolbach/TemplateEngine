/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.List;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolTable;
import org.soulspace.template.symbols.impl.SymbolType;

public class ForeachNode extends AstNode {

  /**
   * 
   */
  public ForeachNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public ForeachNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.FOREACH);
  }

	public ISymbol generateSymbol() {
    StringBuilder sb = new StringBuilder(128);
    setSymbolTable(new SymbolTable());
    
    List<ISymbol> list;

    if(getChildNodes().size() != 3) {
      throw new GenerateException("Syntax error in foreach!");      
    }
    
    IdentifierNode id = (IdentifierNode) getChild(0);
    String elName = id.getData();
    
    if(lookupSymbolInBlock(id.getData()) != null) {
      throw new GenerateException("Symbol already exists: " + id.getData());
    }
    
    IAstNode loopNode = getChild(1);

    // lookup loop variable
    ISymbol symbol = getSymbol(loopNode);
    if (symbol == null) {
      // Missing Variable
      throw new GenerateException("Variable " + loopNode.getData()
          + " is not initialized!");
    }

    // Check type of the variable
    if (!(symbol.getType() == SymbolType.LIST)) {
      // Variable not of type LIST
      throw new GenerateException("Expecting variable of type LIST!");
    }
    ListSymbol lSymbol = (ListSymbol) symbol;
    // Get List to iterate over
    list = lSymbol.getData();

    for (int i = 0; i < list.size(); i++) {
      // Set iteration variables
      lSymbol.setIndex(i);
      lSymbol.setEntry(list.get(i));
      getSymbolTable().addSymbol(elName, list.get(i));

      // Execute block
      sb.append(getChild(2).generateSymbol().evaluate());
    }

    // Clear 'ENTRY' reference
    lSymbol.setEntry(null);

    //System.out.append(sb.toString());
    return new StringSymbol(sb.toString());
	}
}
