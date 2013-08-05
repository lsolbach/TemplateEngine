/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.method.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class Utf8ToIsoLatinMethodImpl extends AbstractMethod {

	private static final String NAME = "utf8ToLatin1";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}
	
	public Utf8ToIsoLatinMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
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
		return new StringValueImpl(latin1);
	}

}
