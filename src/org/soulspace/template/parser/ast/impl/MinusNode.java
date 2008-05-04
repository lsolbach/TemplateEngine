/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class MinusNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public MinusNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public MinusNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.MINUS);
  }

	public ISymbol generateSymbol() {
    if(getChildNodes().size() == 1) {
      // unary minus
    	NumericSymbol result = asNumeric(getChild(0).generateSymbol());
      return result.mult(new NumericSymbol(-1.0));
    } else {
      NumericSymbol result = asNumeric(getChild(0).generateSymbol());
      int n = getChildNodes().size();
      for(int i = 1; i < n; i++) {
        // substract further values
        result = result.substract(asNumeric(getChild(i).generateSymbol()));
      }
      return result;
    }
	}
}
