package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class ReplaceMethodImpl extends AbstractMethod {

	private static final String NAME = "replace";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(StringValue.class);
	}
	
	
	public ReplaceMethodImpl() {
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}

	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		IValue result = null;

		IStringValue value = (IStringValue) arguments.get(0);
		IStringValue charValue = (IStringValue) arguments.get(1);
		IStringValue replacementValue = (IStringValue) arguments.get(1);
		String string = value.getData();
		if(string.length() == 0) {
			result = new StringValue(string);
		} else {
			result = new StringValue(string.replace(charValue.getData(), replacementValue.getData()));
		}		
		return result;
	}

}
