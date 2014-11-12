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
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class MapPutMethodImpl extends AbstractMethod {

	private static final String NAME = "put";
	protected static final Class<? extends Value> RETURN_TYPE = MapValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(MapValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(Value.class);
	}

	public MapPutMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		MapValueImpl mapSymbol = (MapValueImpl) arguments.get(0);
		StringValue key = (StringValue) arguments.get(1);
		Value value = arguments.get(2);
		
		mapSymbol.getData().addSymbol(key.getData(), value);

		return mapSymbol;
	}

}
