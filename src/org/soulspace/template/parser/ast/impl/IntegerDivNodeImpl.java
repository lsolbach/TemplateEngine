/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
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
