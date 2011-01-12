package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;

public class HasNextElementMethodImpl extends AbstractMethod {

	private static final String NAME = "hasNext";
	protected static final Class<? extends Value> RETURN_TYPE = NumericValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(ListValueImpl.class);
	}

	public HasNextElementMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value value = arguments.get(0);
		if(((ListValue) value).hasNext()) {
			return new NumericValueImpl(1);				
		}
		return new NumericValueImpl(0);
	}
}
