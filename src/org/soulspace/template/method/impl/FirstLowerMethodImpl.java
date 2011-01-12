package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class FirstLowerMethodImpl extends AbstractMethod {

	private static final String NAME = "firstLower";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}
	
	
	public FirstLowerMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value result = null;

		StringValue value = (StringValue) arguments.get(0);
		String string = value.getData();
		if(string.length() == 0) {
			result = new StringValueImpl(string);
		} else if(string.length() == 1) {
			result = new StringValueImpl(string.toLowerCase());
		} else {
			result = new StringValueImpl(string.substring(0, 1).toLowerCase() + string.substring(1));
		}
		
		return result;
	}

}
