package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.MapValue;
import org.soulspace.template.value.impl.StringValue;

public class MapClearMethodImpl extends AbstractMethod {

	private static final String NAME = "clearMap";
	protected static final Class<? extends IValue> RETURN_TYPE = MapValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(MapValue.class);
	}


	public MapClearMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		MapValue mapSymbol = (MapValue) arguments.get(0);

		if(mapSymbol.getData().getSymbolCount() > 0) {
			mapSymbol = new MapValue();
		}

		return mapSymbol;
	}


}
