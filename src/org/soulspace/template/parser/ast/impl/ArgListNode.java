/*
 * Created on Nov 11, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbol;

public class ArgListNode extends AstNode {

  public ArgListNode() {
    this(null);
  }

  public ArgListNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ARG_LIST);
  }

	public ISymbol generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on ArgListNode");
	}

}
