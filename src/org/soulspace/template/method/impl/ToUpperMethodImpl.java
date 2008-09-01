package org.soulspace.template.method.impl;

import java.util.List;

import org.soulspace.template.method.StringToStringMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class ToUpperMethodImpl extends StringToStringMethod {

	private static final String NAME = "toUpper";

	public ToUpperMethodImpl() {
		super();
		this.name = NAME;
	}
	
	public IValue doEvaluation(List<IValue> arguments) {
		IValue result = null;

		// evaluate
		IStringValue string = (IStringValue) arguments.get(0);
		result = new StringValue(string.getData().toUpperCase());
		
		return result;
	}

}
