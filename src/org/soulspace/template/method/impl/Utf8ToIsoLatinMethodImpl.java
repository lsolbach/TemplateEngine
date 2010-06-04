package org.soulspace.template.method.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
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

//		Charset charset = Charset.forName("ISO-8859-1");
//		CharsetDecoder decoder = charset.newDecoder();
//		CharsetEncoder encoder = charset.newEncoder();
		
		// FIXME implement
		String latin1 = utf8;
		try {
			latin1 = new String(utf8.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// do nothing, return the old value
			e.printStackTrace();
		}
		return new StringValue(latin1);
	}

}