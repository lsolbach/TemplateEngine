/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class TextNode extends AstNode {

  public TextNode() {
    this(null);
  }

  public TextNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.TEXT);
  }

	public ISymbol generateSymbol() {
		return new StringSymbol(getData());
	}

}
