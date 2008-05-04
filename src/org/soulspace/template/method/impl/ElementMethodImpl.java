package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;

public class ElementMethodImpl extends AbstractMethod {

	private static final String NAME = "element";
	protected static final Class<? extends ISymbol> RETURN_TYPE = ISymbol.class;
	protected static final List<Class<? extends ISymbol>> DEFINED_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final List<Class<? extends ISymbol>> ARGUMENT_TYPES = new ArrayList<Class<? extends ISymbol>>();

	static {
		DEFINED_TYPES.add(ListSymbol.class);
	}

	public ElementMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		ISymbol value = arguments.get(0);
		return ((ListSymbol) value).getEntry();
	}

}
