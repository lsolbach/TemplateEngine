/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;

public class LessNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public LessNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public LessNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.LESS);
  }

	public Value generateValue() {
		NumericValue op1 = asNumeric(getChild(0).generateValue());
		NumericValue op2 = asNumeric(getChild(1).generateValue());
		return op1.less(op2);
	}

}
