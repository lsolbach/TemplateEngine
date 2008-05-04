/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class DivNode extends ExpressionNode implements IExpressionNode {

  
  /**
   * 
   */
  public DivNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public DivNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.DIV);
  }

	public ISymbol generateSymbol() {
    NumericSymbol result = asNumeric(getChild(0).generateSymbol());

    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // divide further values
    	NumericSymbol divisor = asNumeric(getChild(i).generateSymbol());
    	result = result.divide(divisor);
    }
    return result;
	}
}
