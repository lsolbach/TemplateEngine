package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;

public class GetElementMethodImpl extends AbstractMethod {

	private static final String NAME = "element";
	protected static final Class<? extends IValue> RETURN_TYPE = IValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(ListValue.class);
	}

	public GetElementMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		IValue value = arguments.get(0);
		return ((IListValue) value).getEntry();
	}

}