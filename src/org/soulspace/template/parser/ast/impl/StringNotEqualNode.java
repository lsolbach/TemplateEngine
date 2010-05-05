/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class StringNotEqualNode extends AbstractAstNode {

  /**
   * 
   */
  public StringNotEqualNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringNotEqualNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_NOT_EQUAL);
  }

	public IValue generateSymbol() {
		IStringValue s0 = asString(getChild(0).generateSymbol());
		IStringValue s1 = asString(getChild(1).generateSymbol());

		return new NumericValue((!s0.equals(s1)) ? 1 : 0);			
	}

}
