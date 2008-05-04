/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class PlusNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public PlusNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public PlusNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.PLUS);
  }

	public ISymbol generateSymbol() {
    NumericSymbol result = new NumericSymbol(0.0);
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
      result = result.add(asNumeric(it.next().generateSymbol()));
    }
		return result;
	}
}