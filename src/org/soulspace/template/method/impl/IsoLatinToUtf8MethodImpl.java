package org.soulspace.template.method.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class IsoLatinToUtf8MethodImpl extends AbstractMethod {

	private static final String NAME = "latin1ToUtf8";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}
	
	public IsoLatinToUtf8MethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		String latin1 = arguments.get(0).evaluate();

		// FIXME implement
		String utf8 = latin1;
		try {
			latin1 = new String(latin1.getBytes("ISO-8851-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing, return the old value
			e.printStackTrace();
		}
		return new StringValue(latin1);
	}

}
