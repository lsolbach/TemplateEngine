/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;

public class GreaterNode extends AbstractAstNode {

  /**
   * 
   */
  public GreaterNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public GreaterNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.GREATER);
  }

	public IValue generateSymbol() {
		INumericValue op1 = asNumeric(getChild(0).generateSymbol());
		INumericValue op2 = asNumeric(getChild(1).generateSymbol());
		return op1.greater(op2);
	}

}
