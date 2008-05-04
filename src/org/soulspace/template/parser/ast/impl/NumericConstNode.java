/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class NumericConstNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public NumericConstNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public NumericConstNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.NUMERIC_CONST);
  }

	public ISymbol generateSymbol() {
		return new NumericSymbol(getData());
	}

}
