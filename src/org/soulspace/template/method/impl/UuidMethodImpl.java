package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class UuidMethodImpl extends AbstractMethod {

	private static final String NAME = "uuid";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}
	
	public UuidMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		return new StringValueImpl(UUID.randomUUID().toString());
	}

}
