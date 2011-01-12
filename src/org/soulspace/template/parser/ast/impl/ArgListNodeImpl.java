/*
 * Created on Nov 11, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;

public class ArgListNodeImpl extends AbstractAstNode {

  public ArgListNodeImpl() {
    this(null);
  }

  public ArgListNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.ARG_LIST);
  }

	public Value generateValue() {
		throw new GenerateException("Method generateSymbol() must not be called on ArgListNodeImpl! Template " + getTemplate() + ", line " + getLine());
	}

}
