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

public class MultNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public MultNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public MultNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.MULT);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		NumericValueImpl result = new NumericValueImpl(1.0);
		Iterator<AstNode> it = getChildNodes().iterator();
		while (it.hasNext()) {
			result = result.mult(asNumeric(it.next().generateValue(environment)));
		}
		return result;
	}
}
