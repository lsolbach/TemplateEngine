/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class TextNode extends AbstractAstNode {

  public TextNode() {
    this(null);
  }

  public TextNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.TEXT);
  }

	public IValue generateSymbol() {
		return new StringValue(getData());
	}

}
