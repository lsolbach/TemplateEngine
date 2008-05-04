/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class LogicalOrNode extends ExpressionNode implements IExpressionNode {

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

	public ISymbol generateSymbol() {
    ISymbol result = null;
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
    	result = it.next().generateSymbol();
      if(result != null && result.isTrue()) {
        // lazy evaluation, return if result is true
        return new NumericSymbol(1);
      }
    }
    return new NumericSymbol(0);
	}

}
