package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class ToUpperMethodImpl extends AbstractMethod {

	private static final String NAME = "toUpper";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}

	public ToUpperMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	public IValue doEvaluation(List<IValue> arguments) {
		IValue result = null;

		// evaluate
		IStringValue string = (IStringValue) arguments.get(0);
		result = new StringValue(string.getData().toUpperCase());
		
		return result;
	}

}
