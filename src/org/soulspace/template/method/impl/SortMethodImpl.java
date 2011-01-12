package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MethodValueImpl;

public class SortMethodImpl extends AbstractMethod {

	private static final String NAME = "add";
	protected static final Class<? extends Value> RETURN_TYPE = ListValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(ListValue.class);
		ARGUMENT_TYPES.add(MethodValue.class);
	}

	public SortMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected Value doEvaluation(List<Value> arguments) {
		ListValueImpl list = (ListValueImpl) arguments.get(0);
		MethodValueImpl method = (MethodValueImpl) arguments.get(0);

		
		return null;
	}

}
