package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.IMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.NumericSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class StartsWithMethodImpl extends AbstractMethod implements IMethod {

	private static final String NAME = "startsWith";
	protected static final Class<? extends ISymbol> RETURN_TYPE = NumericSymbol.class;
	protected static final List<Class<? extends ISymbol>> DEFINED_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final List<Class<? extends ISymbol>> ARGUMENT_TYPES = new ArrayList<Class<? extends ISymbol>>();

	static {
		DEFINED_TYPES.add(StringSymbol.class);
		ARGUMENT_TYPES.add(StringSymbol.class);
	}

	public StartsWithMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		ISymbol result = null;

		StringSymbol string = (StringSymbol) arguments.get(0);
		StringSymbol prefix = (StringSymbol) arguments.get(1);
		if(string.getData().startsWith(prefix.getData())) {
			result = new NumericSymbol(1);
		} else {
			result = new NumericSymbol(0);			
		}
		return result;
	}
}
