/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
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

	public Value generateValue() {
		StringBuffer sb = new StringBuffer(128);

		Value exSymbol = getChild(0).generateValue();
		while (exSymbol != null && exSymbol.isTrue()) {
			sb.append(getChild(1).generateValue().evaluate());
			exSymbol = getChild(0).generateValue();
		}
		return new StringValueImpl(sb.toString());
	}

}
