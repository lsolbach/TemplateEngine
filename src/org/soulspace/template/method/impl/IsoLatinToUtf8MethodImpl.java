package org.soulspace.template.method.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class IsoLatinToUtf8MethodImpl extends AbstractMethod {

	private static final String NAME = "latin1ToUtf8";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}
	
	public IsoLatinToUtf8MethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		String latin1 = arguments.get(0).evaluate();

		// FIXME implement
		String utf8 = latin1;
		try {
			latin1 = new String(latin1.getBytes("ISO-8851-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// do nothing, return the old value
			e.printStackTrace();
		}
		return new StringValueImpl(latin1);
	}

}
