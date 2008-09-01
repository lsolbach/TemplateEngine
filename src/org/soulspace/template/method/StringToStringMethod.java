package org.soulspace.template.method;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public abstract class StringToStringMethod extends AbstractMethod {

	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}
	
	public StringToStringMethod() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}

}
