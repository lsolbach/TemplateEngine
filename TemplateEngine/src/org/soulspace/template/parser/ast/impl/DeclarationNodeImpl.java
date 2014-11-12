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
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.MethodValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class DeclarationNodeImpl extends AbstractAstNode {

	/**
	 * 
	 */
	public DeclarationNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public DeclarationNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.DECLARATION);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		AstNode child = null;
		Value symbol = null;
		String name = "";
		String type = getData();

		if ((child = getChild(0)) == null) {
			throw new GenerateException(
					"No identifier given for declaration! Template "
							+ getTemplate() + ", line " + getLine());
		}
		
		if (child instanceof IdentifierNodeImpl) {
			name = child.getData();
			symbol = lookupSymbolInBlock(name);
		} else if (child instanceof AssignNodeImpl) {
			// get left hand side of the assignment
			name = child.getChild(0).getData();
			symbol = lookupSymbolInBlock(name);
		}
		
		if (symbol != null) {
			throw new GenerateException("Symbol already exists: " + name
					+ "! Template " + getTemplate() + ", line " + getLine());
		}

		if (type.equals("string")) {
			environment.addValue(name, new StringValueImpl(""));
		} else if (type.equals("numeric")) {
			environment.addValue(name, new NumericValueImpl(0));
		} else if (type.equals("list")) {
			environment.addValue(name, new ListValueImpl());
		} else if (type.equals("map")) {
			environment.addValue(name, new MapValueImpl());
		} else if(type.equals("method")) {
			MethodValue mValue = new MethodValueImpl(name);
			environment.addValue(name, mValue);
			mValue.setEnvironment(getEnvironment());
		}
		if (child instanceof AssignNodeImpl) {
			// evaluate assign expression
			child.generateValue(environment);
		}

		return new StringValueImpl("");
	}

}
