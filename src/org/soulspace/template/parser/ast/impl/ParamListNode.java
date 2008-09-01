package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.IValue;

public class ParamListNode extends AbstractAstNode {

	public ParamListNode() {
		setType(AstNodeType.PARAM_LIST);
	}

	// just a container for the parameters (types and names) of a method call 
	public IValue generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on ParamListNode");
	}

}
