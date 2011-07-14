/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class NumericConstNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public NumericConstNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public NumericConstNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.NUMERIC_CONST);
  }

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		return new NumericValueImpl(getData());
	}

}
