package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;

public class ListAddMethodImpl extends AbstractMethod {

	private static final String NAME = "add";
	protected static final Class<? extends IValue> RETURN_TYPE = ListValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(ListValue.class);
		ARGUMENT_TYPES.add(IValue.class);
	}

	public ListAddMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		ListValue list = (ListValue) arguments.get(0);
		IValue value = arguments.get(1);
		list.addSymbol(value);
		return list;
	}

}
