package org.soulspace.template.method.impl;

import java.util.List;

import org.soulspace.template.method.StringToStringMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class ToLowerMethodImpl extends StringToStringMethod {

	private static final String NAME = "toLower";
		
	public ToLowerMethodImpl() {
		super();
		this.name = NAME;
	}
	
	public ISymbol doEvaluation(List<ISymbol> arguments) {
		ISymbol result = null;

		StringSymbol string = (StringSymbol) arguments.get(0);
		result = new StringSymbol(string.getData().toLowerCase());
		
		return result;
	}

}
