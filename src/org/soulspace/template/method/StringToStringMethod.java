package org.soulspace.template.method;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public abstract class StringToStringMethod extends AbstractMethod {

	protected static final List<Class<? extends ISymbol>> DEFINED_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final List<Class<? extends ISymbol>> ARGUMENT_TYPES = new ArrayList<Class<? extends ISymbol>>();
	protected static final Class<? extends ISymbol> RETURN_TYPE = StringSymbol.class;

	static {
		DEFINED_TYPES.add(StringSymbol.class);
	}
	
	public StringToStringMethod() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}

}
