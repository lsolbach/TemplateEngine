package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;
import org.soulspace.template.value.impl.StringValue;

public class SplitMethodImpl extends AbstractMethod {

	private static final String NAME = "split";
	protected static final Class<? extends IValue> RETURN_TYPE = ListValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(StringValue.class);
	}

	public SplitMethodImpl() {
		super();
		this.definedTypes = DEFINED_TYPES;
		this.argumentTypes = ARGUMENT_TYPES;
		this.returnType = RETURN_TYPE;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		ListValue result = new ListValue();
		
		String string = ((IStringValue) arguments.get(0)).getData();
		String pattern = ((IStringValue) arguments.get(1)).getData();
		String[] strings = string.split(pattern);
		
		for(String part : strings) {
			result.addNewStringSymbol(part);
		}
		return result;
	}

}
