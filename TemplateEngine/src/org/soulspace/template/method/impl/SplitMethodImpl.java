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

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class SplitMethodImpl extends AbstractMethod {

	private static final String NAME = "split";
	protected static final Class<? extends Value> RETURN_TYPE = ListValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}

	public SplitMethodImpl() {
		super();
		this.definedTypes = DEFINED_TYPES;
		this.argumentTypes = ARGUMENT_TYPES;
		this.returnType = RETURN_TYPE;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		ListValueImpl result = new ListValueImpl();
		String string = ((StringValue) arguments.get(0)).getData();
		String pattern = ((StringValue) arguments.get(1)).getData();
		String[] strings = string.split(pattern);
		
		for(String part : strings) {
			result.addNewStringValue(part);
		}
		return result;
	}

}
