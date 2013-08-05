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

public class LessEqualNodeImpl extends AbstractAstNode {

	/**
	 * 
	 */
	public LessEqualNodeImpl() {
		this(null);
	}

	/**
	* @param parent
	*/
	public LessEqualNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.LESS_EQUAL);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		NumericValue op1 = asNumeric(getChild(0).generateValue(environment));
		NumericValue op2 = asNumeric(getChild(1).generateValue(environment));
		return op1.lessEqual(op2);
	}
	
}
