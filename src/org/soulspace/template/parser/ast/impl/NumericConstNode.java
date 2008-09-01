/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class NumericConstNode extends AbstractAstNode {

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

	public IValue generateSymbol() {
		return new NumericValue(getData());
	}

}
