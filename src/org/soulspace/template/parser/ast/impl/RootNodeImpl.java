/*
 * Created on Apr 28, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class RootNodeImpl extends AbstractAstNode {

  public RootNodeImpl() {
    this(null);
  }

  public RootNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.ROOT);
    // initMethodTable();
    initMethodRegistry();
  }

	public Value generateValue() {
		if(getChild(0) != null) {
			return getChild(0).generateValue();
		}		
		return new StringValueImpl("");
	}
}
