package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;

public class ParamListNodeImpl extends AbstractAstNode {

	public ParamListNodeImpl() {
		setType(AstNodeType.PARAM_LIST);
	}

	// just a container for the parameters (types and names) of a method call 
	public Value generateValue() {
		throw new GenerateException("Method generateSymbol() must not be called on ParamListNodeImpl! Template "
				+ getTemplate() + ", line " + getLine());
	}

}
