/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class LessNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public LessNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public LessNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.LESS);
  }

	public ISymbol generateSymbol() {
		NumericSymbol op1 = asNumeric(getChild(0).generateSymbol());
		NumericSymbol op2 = asNumeric(getChild(1).generateSymbol());
		return op1.less(op2);
	}

}
