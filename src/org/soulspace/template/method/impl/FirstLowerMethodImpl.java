package org.soulspace.template.method.impl;

import java.util.List;

import org.soulspace.template.method.StringToStringMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class FirstLowerMethodImpl extends StringToStringMethod {

	private static final String NAME = "firstLower";

	public FirstLowerMethodImpl() {
		super();
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		ISymbol result = null;

		StringSymbol value = (StringSymbol) arguments.get(0);
		String string = value.getData();
		result = new StringSymbol(string.substring(0, 1).toLowerCase() + string.substring(1));
		
		return result;
	}

}
