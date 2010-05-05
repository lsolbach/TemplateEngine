/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class WhileNode extends AbstractAstNode {

	/**
   * 
   */
	public WhileNode() {
		this(null);
	}

	/**
	 * @param parent
	 */
	public WhileNode(IAstNode parent) {
		super(parent);
		setType(AstNodeType.WHILE);
	}

	public IValue generateSymbol() {
		StringBuffer sb = new StringBuffer(128);

		IValue exSymbol = getChild(0).generateSymbol();
		while (exSymbol != null && exSymbol.isTrue()) {
			sb.append(getChild(1).generateSymbol().evaluate());
			exSymbol = getChild(0).generateSymbol();
		}
		return new StringValue(sb.toString());
	}

}
