package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.IMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.NumericValue;
import org.soulspace.template.value.impl.StringValue;

public class IndexOfMethodImpl extends AbstractMethod implements IMethod {

	private static final String NAME = "indexOf";
	protected static final Class<? extends IValue> RETURN_TYPE = NumericValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(StringValue.class);
	}

	public IndexOfMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;	
	}
	
	protected IValue doEvaluation(List<IValue> arguments) {
		IValue result = null;

		IStringValue string = (IStringValue) arguments.get(0);
		IStringValue pattern = (IStringValue) arguments.get(1);
		int index = string.getData().indexOf(pattern.getData());
		result = new NumericValue(index);
		return result;
	}

}
