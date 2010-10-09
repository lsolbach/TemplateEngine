package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IListValue;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.IStringValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;
import org.soulspace.template.value.impl.MapValue;
import org.soulspace.template.value.impl.NumericValue;
import org.soulspace.template.value.impl.StringValue;

public class SizeMethodImpl extends AbstractMethod {

	private static final String NAME = "size";
	protected static final Class<? extends IValue> RETURN_TYPE = NumericValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(StringValue.class);
		DEFINED_TYPES.add(ListValue.class);
		DEFINED_TYPES.add(MapValue.class);
	}

	public SizeMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		IValue value = arguments.get(0);
		if(value instanceof IListValue) {
			return new NumericValue(((IListValue) value).getData().size());
		} else if(value instanceof IMapValue) {
			return new NumericValue(((IMapValue) value).getData().getSymbolCount());
		} else if(value instanceof IStringValue) {
			return new NumericValue(((IStringValue) value).getData().length());
		}
		return new NumericValue(0);
	}

}
