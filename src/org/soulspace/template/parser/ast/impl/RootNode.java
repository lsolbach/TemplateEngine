/*
 * Created on Apr 28, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class RootNode extends AbstractAstNode {

  public RootNode() {
    this(null);
  }

  public RootNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.ROOT);
    initMethodTable();
  }

	public IValue generateSymbol() {
		if(getChild(0) != null) {
			return getChild(0).generateSymbol();
		}		
		return new StringValue("");
	}
}
