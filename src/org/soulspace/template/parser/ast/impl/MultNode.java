/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class MultNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public MultNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public MultNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.MULT);
  }

	public ISymbol generateSymbol() {
    NumericSymbol result = new NumericSymbol(1.0);
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
      result = result.mult(asNumeric(it.next().generateSymbol()));
    }
    return result;
	}
}
