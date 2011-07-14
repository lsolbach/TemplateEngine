/*
 * Created on Jul 24, 2005
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
