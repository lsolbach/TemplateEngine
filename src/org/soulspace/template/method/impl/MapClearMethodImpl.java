package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.MapValueImpl;

public class MapClearMethodImpl extends AbstractMethod {

	private static final String NAME = "clearMap";
	protected static final Class<? extends Value> RETURN_TYPE = MapValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(MapValueImpl.class);
	}


	public MapClearMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		MapValueImpl mapSymbol = (MapValueImpl) arguments.get(0);

		if(mapSymbol.getData().getSymbolCount() > 0) {
			mapSymbol = new MapValueImpl();
		}

		return mapSymbol;
	}


}
