/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class DivNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public DivNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public DivNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.DIV);
	}

	public Value generateValue() {
		NumericValueImpl result = asNumeric(getChild(0).generateValue());

		int n = getChildNodes().size();
		for (int i = 1; i < n; i++) {
			// divide further values
			NumericValue divisor = asNumeric(getChild(i).generateValue());
			result = result.divide(divisor);
		}
		return result;
	}
}
