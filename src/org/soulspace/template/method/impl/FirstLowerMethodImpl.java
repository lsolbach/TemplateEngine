package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class FirstLowerMethodImpl extends AbstractMethod {

	private static final String NAME = "firstLower";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}
	
	
	public FirstLowerMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
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
