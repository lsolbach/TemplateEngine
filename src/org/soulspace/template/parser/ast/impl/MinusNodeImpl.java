/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;

public class MinusNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public MinusNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public MinusNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.MINUS);
	}

	public Value generateValue() {
		if (getChildNodes().size() == 1) {
			// unary minus
			NumericValue result = asNumeric(getChild(0).generateValue());
			return result.mult(new NumericValueImpl(-1.0));
		} else {
			NumericValueImpl result = asNumeric(getChild(0).generateValue());
			int n = getChildNodes().size();
			for (int i = 1; i < n; i++) {
				// substract further values
				result = result.substract(asNumeric(getChild(i)
						.generateValue()));
			}
			return result;
		}
	}
}
