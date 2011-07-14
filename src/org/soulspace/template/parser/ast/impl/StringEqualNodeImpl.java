/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class StringEqualNodeImpl extends AbstractAstNode {

  /**
   * 
   */
  public StringEqualNodeImpl() {
    this(null);
  }

  /**
   * @param parent
   */
  public StringEqualNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.STRING_EQUAL);
  }

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		StringValue s0 = asString(getChild(0).generateValue(environment));
		StringValue s1 = asString(getChild(1).generateValue(environment));

		return new NumericValueImpl((s0.equals(s1)) ? 1 : 0);			
	}

}
