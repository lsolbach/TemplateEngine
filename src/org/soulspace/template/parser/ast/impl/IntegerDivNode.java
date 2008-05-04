/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class IntegerDivNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public IntegerDivNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public IntegerDivNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.IDIV);
  }

	public ISymbol generateSymbol() {
    NumericSymbol result = asNumeric(getChild(0).generateSymbol());

    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // divide further values
    	NumericSymbol divisor = asNumeric(getChild(i).generateSymbol());
    	result = result.divideInteger(divisor);
    }
    return result;
	}
}
