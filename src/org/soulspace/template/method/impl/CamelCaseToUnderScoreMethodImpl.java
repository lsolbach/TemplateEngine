package org.soulspace.template.method.impl;

import java.util.List;

import org.soulspace.template.method.IMethod;
import org.soulspace.template.method.StringToStringMethod;
import org.soulspace.template.symbols.ISymbol;
import org.soulspace.template.symbols.impl.StringSymbol;

public class CamelCaseToUnderScoreMethodImpl extends StringToStringMethod
		implements IMethod {

	private static final String NAME = "camelCaseToUnderScore";

	public CamelCaseToUnderScoreMethodImpl() {
		super();
		this.name = NAME;
	}
	
	@Override
	protected ISymbol doEvaluation(List<ISymbol> arguments) {
		String camelCase = arguments.get(0).evaluate();
		
		if(camelCase != null && camelCase.length() > 0) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < camelCase.length(); i++) {
				String ch = camelCase.substring(i, i + 1);
				if(ch.equals(ch.toUpperCase())) {
					sb.append("_");
					sb.append(ch.toLowerCase());
				} else {
					sb.append(ch);
				}
			}
			return new StringSymbol(sb.toString());
		} else {
			return arguments.get(0);			
		}
	}

}
