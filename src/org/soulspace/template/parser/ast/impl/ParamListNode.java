package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.symbols.ISymbol;

public class ParamListNode extends AstNode {

	public ParamListNode() {
		setType(AstNodeType.PARAM_LIST);
	}

	// just a container for the parameters (types and names) of a method call 
	public ISymbol generateSymbol() {
		throw new GenerateException("Method generateSymbol() must not be called on ParamListNode");
	}

}
