/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class StringNotEqualNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public StringNotEqualNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringNotEqualNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_NOT_EQUAL);
  }

	public Value generateValue() {
		StringValue s0 = asString(getChild(0).generateValue());
		StringValue s1 = asString(getChild(1).generateValue());

		return new NumericValueImpl((!s0.equals(s1)) ? 1 : 0);			
	}

}
