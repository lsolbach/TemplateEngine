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

import java.util.Iterator;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.Value;

public class DereferenceNodeImpl extends AbstractAstNode {

	public DereferenceNodeImpl() {
		this(null);
	}

	public DereferenceNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.DEREF_EXPR);
	}

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		Iterator<AstNode> it = getChildNodes().iterator();
		AstNode node = null;
		Value value = null;
		
		while (it.hasNext()) {
			node = it.next();
			if (node instanceof IdentifierNodeImpl) {
				// dereference all identifier nodes
				IdentifierNodeImpl idNode = (IdentifierNodeImpl) node;
				if(value == null) {
					// first node a in a:b[1]:c
					value = idNode.generateValue(environment);
				} else {
					// deref further nodes b or c in a:b[1]:c
					value = derefSymbol(value, idNode);
					// array or map lookup on via id node like b[1] in a:b[1]:c
					if(value != null &&
							(value instanceof MapValue || value instanceof Value)) {
						value = idNode.deref(environment, value);
					}
				}
			}
		}
		return value;
	}

}
