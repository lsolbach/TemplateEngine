/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class LogicalNotNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public LogicalNotNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public LogicalNotNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.LOGICAL_NOT);
  }

	public ISymbol generateSymbol() {
		ISymbol result = getChild(0).generateSymbol();
		if(result == null || !result.isTrue()) {
      return new NumericSymbol(1);
    }
    return new NumericSymbol(0);
	}

}
