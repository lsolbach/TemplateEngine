package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class RoundMethodImpl extends AbstractMethod {

	private static final String NAME = "round";
	protected static final Class<? extends Value> RETURN_TYPE = NumericValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}

	public RoundMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value result = null;

		NumericValue number = (NumericValue) arguments.get(0);
		NumericValue digits = (NumericValue) arguments.get(1);

		// TODO implement rounding
		
		return number;
	}
}
