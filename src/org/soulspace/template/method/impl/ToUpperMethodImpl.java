package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class ToUpperMethodImpl extends AbstractMethod {

	private static final String NAME = "toUpper";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}

	public ToUpperMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	public Value doEvaluation(List<Value> arguments) {
		Value result = null;

		// evaluate
		StringValue string = (StringValue) arguments.get(0);
		result = new StringValueImpl(string.getData().toUpperCase());
		
		return result;
	}

}
