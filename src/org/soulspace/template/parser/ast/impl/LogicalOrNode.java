/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class LogicalOrNode extends AbstractAstNode {

  /**
   * 
   */
  public LogicalOrNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public LogicalOrNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.LOGICAL_OR);
  }

	public IValue generateSymbol() {
    IValue result = null;
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
    	result = it.next().generateSymbol();
      if(result != null && result.isTrue()) {
        // lazy evaluation, return if result is true
        return new NumericValue(1);
      }
    }
    return new NumericValue(0);
	}

}
