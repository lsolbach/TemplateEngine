/*
 * Created on Nov 11, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;

public class ArgListNode extends AbstractAstNode {

  public ArgListNode() {
    this(null);
  }

  public ArgListNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ARG_LIST);
  }

	public IValue generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on ArgListNode");
	}

}
