package org.soulspace.template.method.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class Utf8ToIsoLatinMethodImpl extends AbstractMethod {

	private static final String NAME = "utf8ToLatin1";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}
	
	public Utf8ToIsoLatinMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		String utf8 = arguments.get(0).evaluate();

		// FIXME implement
		String latin1 = utf8;
		try {
			latin1 = new String(utf8.getBytes("utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		return new StringValue(latin1);
	}

}
