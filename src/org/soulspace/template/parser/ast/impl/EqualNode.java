/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class EqualNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public EqualNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public EqualNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.EQUAL);
  }

	public ISymbol generateSymbol() {
		NumericSymbol op1 = asNumeric(getChild(0).generateSymbol());
		NumericSymbol op2 = asNumeric(getChild(1).generateSymbol());
		return op1.equal(op2);
	}
}
