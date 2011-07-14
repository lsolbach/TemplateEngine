/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class LogicalOrNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public LogicalOrNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public LogicalOrNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.LOGICAL_OR);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		Value result = null;
		Iterator<AstNode> it = getChildNodes().iterator();
		while (it.hasNext()) {
			result = it.next().generateValue(environment);
			if (result != null && result.isTrue()) {
				// lazy evaluation, return if result is true
				return new NumericValueImpl(1);
			}
		}
		return new NumericValueImpl(0);
	}

}
