/*
 * Created on Jul 24, 2005
 */
package org.soulspace.template.parser.ast.impl;

import java.util.Iterator;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;

public class DereferenceNodeImpl extends AbstractAstNode {

	public DereferenceNodeImpl() {
		this(null);
	}

	public DereferenceNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.DEREF_EXPR);
	}

	public Value generateValue() {
		Iterator<AstNode> it = getChildNodes().iterator();
		AstNode node = null;
		Value symbol = null;
		while (it.hasNext()) {
			node = it.next();
			if (node instanceof IdentifierNodeImpl) {
				// dereference all identifier nodes
				IdentifierNodeImpl idNode = (IdentifierNodeImpl) node;
				if (symbol == null) {
					// lookup symbol in symbol tables
					symbol = idNode.lookupSymbol(idNode.getData());
				} else {
					// dereference
					symbol = idNode.derefSymbol(symbol, idNode.getData());
				}
				// array or map lookup
				symbol = idNode.lookup(symbol);
			}
		}
		return symbol;
	}
}
