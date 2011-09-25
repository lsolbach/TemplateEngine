/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;

public class GreaterNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public GreaterNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public GreaterNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.GREATER);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		NumericValue op1 = asNumeric(getChild(0).generateValue(environment));
		NumericValue op2 = asNumeric(getChild(1).generateValue(environment));
		return op1.greater(op2);
	}

}