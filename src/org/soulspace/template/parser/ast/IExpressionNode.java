package org.soulspace.template.parser.ast;

import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public interface IExpressionNode extends IAstNode {
	ISymbol generateSymbol();
	NumericSymbol asNumeric(ISymbol symbol);
	StringSymbol asString(ISymbol symbol);
}
