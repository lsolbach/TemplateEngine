/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class ModuloNode extends ExpressionNode implements IExpressionNode {

  /**
   * 
   */
  public ModuloNode() {
    this(null);
  }

  /**
   * @param parent
   */
  public ModuloNode(IAstNode parent) {
    super(parent);
    setType(AstNodeType.MODULO);
  }

	public ISymbol generateSymbol() {
    NumericSymbol result = asNumeric(getChild(0).generateSymbol());
    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // modulo for further values
    	NumericSymbol operand = asNumeric(getChild(i).generateSymbol());
      result = result.modulo(operand);
    }
    return result;
	}
}