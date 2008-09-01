/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class LogicalAndNode extends AbstractAstNode {

  /**
   * 
   */
  public LogicalAndNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public LogicalAndNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.LOGICAL_AND);
  }

	public IValue generateSymbol() {
    IValue result = null;
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
    	result = it.next().generateSymbol();
      if(result == null || !result.isTrue()) {
        // lazy evaluation, return if result is false
        return new NumericValue(0);
      }
    }
    return new NumericValue(1);
	}

}
