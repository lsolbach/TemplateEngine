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
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class WhileNodeImpl extends AbstractAstNode {

	/**
	 * 
	 */
	public WhileNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public WhileNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.WHILE);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		StringBuffer sb = new StringBuffer(128);

		Value exSymbol = getChild(0).generateValue(environment);
		while (exSymbol != null && exSymbol.isTrue()) {
			sb.append(getChild(1).generateValue(environment).evaluate());
			exSymbol = getChild(0).generateValue(environment);
		}
		return new StringValueImpl(sb.toString());
	}

}
