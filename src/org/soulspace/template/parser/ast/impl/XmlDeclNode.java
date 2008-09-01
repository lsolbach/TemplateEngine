/*
 * Created on Apr 24, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class XmlDeclNode extends AbstractAstNode implements IAstNode {

  public XmlDeclNode() {
    this(null);
  }

  public XmlDeclNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.XML_DECL);
  }

	public IValue generateSymbol() {
		return new StringValue(getData());
	}

}
