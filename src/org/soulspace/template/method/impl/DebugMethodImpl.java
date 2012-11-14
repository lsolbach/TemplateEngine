package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.MethodValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class DebugMethodImpl extends AbstractMethod {

	private static final String NAME = "debug";
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(NumericValueImpl.class);
		DEFINED_TYPES.add(StringValueImpl.class);
		DEFINED_TYPES.add(ListValueImpl.class);
		DEFINED_TYPES.add(MapValueImpl.class);
		DEFINED_TYPES.add(MethodValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}

	public DebugMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value value = arguments.get(0);
		if(arguments.size() > 1) {
			StringValue messageValue = (StringValue) arguments.get(1);
			System.err.println(messageValue.asString() + " " + value.asString());
		} else {
			System.err.println(value.asString());
		}
		return new StringValueImpl();
	}

}
