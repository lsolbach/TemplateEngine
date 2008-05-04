/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class StringConstNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public StringConstNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringConstNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_CONST);
  }

	public ISymbol generateSymbol() {
		return new StringSymbol(getData());
	}

}
