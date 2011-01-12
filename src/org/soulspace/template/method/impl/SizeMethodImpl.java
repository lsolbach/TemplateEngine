package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;
import org.soulspace.template.value.impl.NumericValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;

public class SizeMethodImpl extends AbstractMethod {

	private static final String NAME = "size";
	protected static final Class<? extends Value> RETURN_TYPE = NumericValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		DEFINED_TYPES.add(ListValueImpl.class);
		DEFINED_TYPES.add(MapValueImpl.class);
	}

	public SizeMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		Value value = arguments.get(0);
		if(value instanceof ListValue) {
			return new NumericValueImpl(((ListValue) value).getData().size());
		} else if(value instanceof MapValue) {
			return new NumericValueImpl(((MapValue) value).getData().getSymbolCount());
		} else if(value instanceof StringValue) {
			return new NumericValueImpl(((StringValue) value).getData().length());
		}
		return new NumericValueImpl(0);
	}

}
