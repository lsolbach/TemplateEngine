package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.MapValue;
import org.soulspace.template.value.impl.StringValue;

public class MapPutMethodImpl extends AbstractMethod {

	private static final String NAME = "put";
	protected static final Class<? extends IValue> RETURN_TYPE = MapValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(MapValue.class);
		ARGUMENT_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(IValue.class);
	}


	public MapPutMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		MapValue mapSymbol = (MapValue) arguments.get(0);
		IStringValue key = (IStringValue) arguments.get(1);
		IValue value = arguments.get(2);
		
		mapSymbol.getData().addSymbol(key.getData(), value);

		return mapSymbol;
	}

}
