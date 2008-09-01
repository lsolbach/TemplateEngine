/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class ModuloNode extends AbstractAstNode {

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

	public IValue generateSymbol() {
    NumericValue result = asNumeric(getChild(0).generateSymbol());
    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // modulo for further values
    	INumericValue operand = asNumeric(getChild(i).generateSymbol());
      result = result.modulo(operand);
    }
    return result;
	}
}