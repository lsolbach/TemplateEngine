/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;

public class MinusNode extends AbstractAstNode {

	/**
   * 
   */
	public MinusNode() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public MinusNode(IAstNode parent) {
		super(parent);
		setType(AstNodeType.MINUS);
	}

	public IValue generateSymbol() {
		if (getChildNodes().size() == 1) {
			// unary minus
			INumericValue result = asNumeric(getChild(0).generateSymbol());
			return result.mult(new NumericValue(-1.0));
		} else {
			NumericValue result = asNumeric(getChild(0).generateSymbol());
			int n = getChildNodes().size();
			for (int i = 1; i < n; i++) {
				// substract further values
				result = result.substract(asNumeric(getChild(i)
						.generateSymbol()));
			}
			return result;
		}
	}
}
