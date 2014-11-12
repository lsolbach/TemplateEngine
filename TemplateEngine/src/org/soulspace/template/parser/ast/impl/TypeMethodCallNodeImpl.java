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

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.method.Method;
import org.soulspace.template.method.impl.MethodRegistryImpl;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;

public class TypeMethodCallNodeImpl extends AbstractAstNode {

	// FIXME make configurable
	public TypeMethodCallNodeImpl() {
		super();
		setType(AstNodeType.TYPE_METHOD_CALL);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		AstNode typeNode = getChild(0);
		Value symbol = typeNode.generateValue(getEnvironment());
		if (symbol == null) {
			throw new GenerateException("Type method call " + getData()
					+ "(). Variable " + typeNode.getData()
					+ " is not initialized! Template " + getTemplate()
					+ ", line " + getLine());
		}

		// lookup type method in type method registry
		Method method = MethodRegistryImpl.lookup(getData());
		if (method == null) {
			throw new GenerateException("Method " + getData()
					+ " not found! Template " + getTemplate() + ", line "
					+ getLine());
		}
		List<Value> args = new ArrayList<Value>();
		args.add(symbol);

		if (getChildCount() == 2) {
			AstNode argListNode = getChild(1);
			for (int i = 0; i < argListNode.getChildCount(); i++) {
				Value s = argListNode.getChild(i).generateValue(environment);
				args.add(s);
			}
		}

		Value result = method.evaluate(args);
		return result;
	}
}
