/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class StringConstNode extends AbstractAstNode {

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

	public IValue generateSymbol() {
		return new StringValue(getData());
	}

}
