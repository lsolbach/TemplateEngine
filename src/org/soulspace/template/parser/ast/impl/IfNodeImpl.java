/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
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

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		Value exSymbol = getChild(0).generateValue(environment);
		try {
			if (exSymbol != null && exSymbol.isTrue()) {
				return getChild(1).generateValue(environment);
			} else if (getChild(2) != null) {
				return getChild(2).generateValue(environment);
			}
			return new StringValueImpl("");
		} catch (NumberFormatException e) {
			throw new GenerateException("Error in expression valuation! Template "
					+ getTemplate() + ", line " + getLine(), e);
		}
	}

}
