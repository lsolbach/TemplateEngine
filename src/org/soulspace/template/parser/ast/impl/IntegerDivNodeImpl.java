/*
 * Created on Apr 19, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class IntegerDivNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public IntegerDivNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public IntegerDivNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.IDIV);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		NumericValueImpl result = asNumeric(getChild(0).generateValue(environment));

		int n = getChildNodes().size();
		for (int i = 1; i < n; i++) {
			// divide further values
			NumericValue divisor = asNumeric(getChild(i).generateValue(environment));
			result = result.divideInteger(divisor);
		}
		return result;
	}
}
