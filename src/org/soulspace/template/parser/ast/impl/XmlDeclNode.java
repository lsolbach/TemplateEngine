/*
 * Created on Apr 24, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class XmlDeclNode extends AstNode implements IAstNode {

  public XmlDeclNode() {
    this(null);
  }

  public XmlDeclNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.XML_DECL);
  }

	public ISymbol generateSymbol() {
		return new StringSymbol(getData());
	}

}
