package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.MapSymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;

public class SizeMethodImpl extends AbstractMethod {

	private static final String NAME = "size";
	protected static final Class<? extends ISymbol> RETURN_TYPE = NumericSymbol.class;
	protected static final List<Class<? extends ISymbol>> DEFINED_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final List<Class<? extends ISymbol>> ARGUMENT_TYPES = new ArrayList<Class<? extends ISymbol>>();

	static {
		DEFINED_TYPES.add(ListSymbol.class);
		DEFINED_TYPES.add(MapSymbol.class);
	}

	public SizeMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		ISymbol value = arguments.get(0);
		if(value instanceof ListSymbol) {
			return new NumericSymbol(((ListSymbol) value).getData().size());
		} else if(value instanceof MapSymbol) {
			return new NumericSymbol(((MapSymbol) value).getData().getSymbolCount());
		}
		return null;
	}

}
