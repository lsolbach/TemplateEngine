/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;


public class IfNode extends AbstractAstNode {

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

	public IValue generateSymbol() {
		IValue exSymbol = getChild(0).generateSymbol();

    if(exSymbol != null && exSymbol.isTrue()) {
      return getChild(1).generateSymbol();      
    } else if(getChild(2) != null) {
      return getChild(2).generateSymbol();
    }
  	return new StringValue("");
	}
  
}
