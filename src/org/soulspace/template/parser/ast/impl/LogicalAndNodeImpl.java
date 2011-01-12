/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class LogicalAndNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public LogicalAndNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public LogicalAndNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.LOGICAL_AND);
	}

	public Value generateValue() {
		Value result = null;
		Iterator<AstNode> it = getChildNodes().iterator();
		while (it.hasNext()) {
			result = it.next().generateValue();
			if (result == null || !result.isTrue()) {
				// lazy evaluation, return if result is false
				return new NumericValueImpl(0);
			}
		}
		return new NumericValueImpl(1);
	}

}
