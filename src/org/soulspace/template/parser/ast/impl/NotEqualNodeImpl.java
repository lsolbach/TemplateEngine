/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;

public class NotEqualNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public NotEqualNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public NotEqualNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.NOT_EQUAL);
  }

	public Value generateValue() {
		NumericValue op1 = asNumeric(getChild(0).generateValue());
		NumericValue op2 = asNumeric(getChild(1).generateValue());
		return op1.notEqual(op2);
	}
}
