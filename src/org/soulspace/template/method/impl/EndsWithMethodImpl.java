package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.Method;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class EndsWithMethodImpl extends AbstractMethod implements Method {

	private static final String NAME = "endsWith";
	protected static final Class<? extends Value> RETURN_TYPE = NumericValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}

	public EndsWithMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value result = null;

		StringValue string = (StringValue) arguments.get(0);
		StringValue prefix = (StringValue) arguments.get(1);
		if(string.getData().endsWith(prefix.getData())) {
			result = new NumericValueImpl(1);
		} else {
			result = new NumericValueImpl(0);			
		}
		return result;
	}
}
