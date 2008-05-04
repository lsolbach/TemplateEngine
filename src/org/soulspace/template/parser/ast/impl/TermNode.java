/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;
import org.soulspace.template.symbols.impl.SymbolTable;

public class TermNode extends AstNode {

  /**
   * 
   */
  public TermNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public TermNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.TERM);
  }

	public ISymbol generateSymbol() {
    setSymbolTable(new SymbolTable());
    StringBuffer sb = new StringBuffer(128);
    Iterator<IAstNode> it = getChildNodes().iterator();
    
    while(it.hasNext()) {
      IAstNode child = it.next();
      try {
    		ISymbol symbol = child.generateSymbol();
    		if(symbol != null) {
      		sb.append(symbol.evaluate());      			
    		}
      } catch (GenerateException e) {
        System.out.println(sb.toString());
        throw e;
      }
    }
    return new StringSymbol(sb.toString());
	}

}
