/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class LogicalNotNode extends AbstractAstNode {

  /**
   * 
   */
  public LogicalNotNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public LogicalNotNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.LOGICAL_NOT);
  }

	public IValue generateSymbol() {
		IValue result = getChild(0).generateSymbol();
		if(result == null || !result.isTrue()) {
      return new NumericValue(1);
    }
    return new NumericValue(0);
	}

}
