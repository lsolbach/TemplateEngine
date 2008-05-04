package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.ListSymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class SplitMethodImpl extends AbstractMethod {

	private static final String NAME = "split";
	protected static final Class<? extends ISymbol> RETURN_TYPE = ListSymbol.class;
	protected static final List<Class<? extends ISymbol>> DEFINED_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final List<Class<? extends ISymbol>> ARGUMENT_TYPES = new ArrayList<Class<? extends ISymbol>>();

	static {
		DEFINED_TYPES.add(StringSymbol.class);
		ARGUMENT_TYPES.add(StringSymbol.class);
	}

	public SplitMethodImpl() {
		super();
		this.definedTypes = DEFINED_TYPES;
		this.argumentTypes = ARGUMENT_TYPES;
		this.returnType = RETURN_TYPE;
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		ListSymbol result = new ListSymbol();
		
		String string = ((StringSymbol) arguments.get(0)).getData();
		String pattern = ((StringSymbol) arguments.get(1)).getData();
		String[] strings = string.split(pattern);
		
		for(String part : strings) {
			result.addNewStringSymbol(part);
		}
		return result;
	}

}
