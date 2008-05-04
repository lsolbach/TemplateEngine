/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;


public class IfNode extends AstNode {

  /**
   * 
   */
  public IfNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public IfNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.IF);
  }

	public ISymbol generateSymbol() {
		ISymbol exSymbol = getChild(0).generateSymbol();

    if(exSymbol != null && exSymbol.isTrue()) {
      return getChild(1).generateSymbol();      
    } else if(getChild(2) != null) {
      return getChild(2).generateSymbol();
    }
  	return new StringSymbol("");
	}
  
}
