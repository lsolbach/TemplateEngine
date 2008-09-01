/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class MultNode extends AbstractAstNode {

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

	public IValue generateSymbol() {
    NumericValue result = new NumericValue(1.0);
    Iterator<IAstNode> it = getChildNodes().iterator();
    while(it.hasNext()) {
      result = result.mult(asNumeric(it.next().generateSymbol()));
    }
    return result;
	}
}
