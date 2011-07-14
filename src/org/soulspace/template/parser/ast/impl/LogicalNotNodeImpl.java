/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class LogicalNotNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public LogicalNotNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public LogicalNotNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.LOGICAL_NOT);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		Value result = getChild(0).generateValue(environment);
		if (result == null || !result.isTrue()) {
			return new NumericValueImpl(1);
		}
		return new NumericValueImpl(0);
	}

}
