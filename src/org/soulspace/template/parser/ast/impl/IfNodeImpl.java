/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class IfNodeImpl extends AbstractAstNode {

	/**
   * 
   */
	public IfNodeImpl() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public IfNodeImpl(AstNode parent) {
		super(parent);
		setType(AstNodeType.IF);
	}

	public Value generateValue() {
		Value exSymbol = getChild(0).generateValue();

		if (exSymbol != null && exSymbol.isTrue()) {
			return getChild(1).generateValue();
		} else if (getChild(2) != null) {
			return getChild(2).generateValue();
		}
		return new StringValueImpl("");
	}

}
