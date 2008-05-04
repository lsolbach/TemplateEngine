package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IExpressionNode;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.MapSymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public abstract class ExpressionNode extends AstNode implements IExpressionNode {

	
	
	public ExpressionNode(IAstNode parent) {
		super(parent);
	}

	public ExpressionNode() {
		super();
	}
	
	public NumericSymbol asNumeric(ISymbol symbol) {
		if(symbol == null) {
			return new NumericSymbol(0);
		}
		if(symbol instanceof NumericSymbol) {
			return (NumericSymbol) symbol;
		} else if(symbol instanceof StringSymbol) {
			return new NumericSymbol(((StringSymbol) symbol).getData());			
		} else if(symbol instanceof ListSymbol) {
			return new NumericSymbol(((ListSymbol) symbol).getData().size());
		} else if(symbol instanceof MapSymbol) {
			return new NumericSymbol(((MapSymbol) symbol).getData().getSymbolCount());
		} else {
			throw new GenerateException("Unknown type: " + symbol.getClass().getSimpleName());
		}
	}

	public StringSymbol asString(ISymbol symbol) {
		if(symbol == null) {
			return new StringSymbol("");
		}
		if(symbol instanceof StringSymbol) {
			return (StringSymbol) symbol;
		} else if(symbol instanceof NumericSymbol) {
			return new StringSymbol(((StringSymbol) symbol).getData());			
		} else if(symbol instanceof ListSymbol) {
			return new StringSymbol(String.valueOf(((ListSymbol) symbol).getData().size()));
		} else if(symbol instanceof MapSymbol) {
			return new StringSymbol(String.valueOf(((MapSymbol) symbol).getData().getSymbolCount()));
		} else {
			throw new GenerateException("Unknown type: " + symbol.getClass().getSimpleName());
		}
	}
}
