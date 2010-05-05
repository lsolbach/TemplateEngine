package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;


public class ListClearMethodImpl extends AbstractMethod {

	private static final String NAME = "clearList";
	protected static final Class<? extends IValue> RETURN_TYPE = ListValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(ListValue.class);
	}

	public ListClearMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		ListValue list = (ListValue) arguments.get(0);
		if(list.size() > 0) {
			list = new ListValue();
		}
		return list;
	}

}
