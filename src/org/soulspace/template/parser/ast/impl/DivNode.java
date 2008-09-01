/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class DivNode extends AbstractAstNode {

  
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

	public IValue generateSymbol() {
    NumericValue result = asNumeric(getChild(0).generateSymbol());

    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // divide further values
    	INumericValue divisor = asNumeric(getChild(i).generateSymbol());
    	result = result.divide(divisor);
    }
    return result;
	}
}
