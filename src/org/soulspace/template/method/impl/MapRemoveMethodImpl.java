package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class MapRemoveMethodImpl extends AbstractMethod {

	private static final String NAME = "remove";
	protected static final Class<? extends Value> RETURN_TYPE = MapValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(MapValueImpl.class);
		ARGUMENT_TYPES.add(StringValueImpl.class);
	}

	public MapRemoveMethodImpl() {
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
		mapSymbol.getData().removeSymbol(key.getData());

		return mapSymbol;
	}

}
