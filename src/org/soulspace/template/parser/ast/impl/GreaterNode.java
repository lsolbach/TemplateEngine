/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class GreaterNode extends ExpressionNode implements IExpressionNode {

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

	public ISymbol generateSymbol() {
		NumericSymbol op1 = asNumeric(getChild(0).generateSymbol());
		NumericSymbol op2 = asNumeric(getChild(1).generateSymbol());
		return op1.greater(op2);
	}

}
