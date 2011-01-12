/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class TextNodeImpl extends AbstractAstNode {

  public TextNodeImpl() {
    this(null);
  }

  public TextNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.TEXT);
  }

	public Value generateValue() {
		return new StringValueImpl(getData());
	}

}
