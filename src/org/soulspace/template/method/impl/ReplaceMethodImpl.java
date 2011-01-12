package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class ReplaceMethodImpl extends AbstractMethod {

	private static final String NAME = "replace";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}
	
	
	public ReplaceMethodImpl() {
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}

	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value result = null;

		StringValue value = (StringValue) arguments.get(0);
		StringValue charValue = (StringValue) arguments.get(1);
		StringValue replacementValue = (StringValue) arguments.get(2);
		String string = value.getData();
		if(string.length() == 0) {
			result = new StringValueImpl(string);
		} else {
			result = new StringValueImpl(string.replace(charValue.getData(), replacementValue.getData()));
		}
		return result;
	}

}
