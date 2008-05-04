/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class WhileNode extends AstNode {

  /**
   * 
   */
  public WhileNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public WhileNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.WHILE);
  }

	public ISymbol generateSymbol() {
    StringBuffer sb = new StringBuffer(128);
    
 		ISymbol exSymbol = getChild(0).generateSymbol();
    while(exSymbol != null && exSymbol.isTrue()) {
      sb.append(getChild(1).generateSymbol().evaluate());
      exSymbol = getChild(0).generateSymbol();
    }
    return new StringSymbol(sb.toString());
	}

}
