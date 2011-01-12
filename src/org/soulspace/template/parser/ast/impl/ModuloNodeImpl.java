/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class ModuloNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public ModuloNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public ModuloNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.MODULO);
  }

	public Value generateValue() {
    NumericValueImpl result = asNumeric(getChild(0).generateValue());
    int n = getChildNodes().size();
    for(int i = 1; i < n; i++) {
      // modulo for further values
    	NumericValue operand = asNumeric(getChild(i).generateValue());
      result = result.modulo(operand);
    }
    return result;
	}
}