package org.soulspace.template.method.impl;

import java.util.List;

import org.soulspace.template.method.StringToStringMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class FirstLowerMethodImpl extends StringToStringMethod {

	private static final String NAME = "firstLower";

	public FirstLowerMethodImpl() {
		super();
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		IValue result = null;

		IStringValue value = (IStringValue) arguments.get(0);
		String string = value.getData();
		if(string.length() == 0) {
			result = new StringValue(string);
		} else if(string.length() == 1) {
			result = new StringValue(string.toLowerCase());
		} else {
			result = new StringValue(string.substring(0, 1).toLowerCase() + string.substring(1));
		}
		
		return result;
	}

}
