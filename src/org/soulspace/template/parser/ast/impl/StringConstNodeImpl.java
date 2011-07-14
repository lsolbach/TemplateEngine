/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class StringConstNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public StringConstNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringConstNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_CONST);
  }

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		return new StringValueImpl(getData());
	}

}
