package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.ListValue;
import org.soulspace.template.value.impl.MapValue;

public class KeyListMethodImpl extends AbstractMethod {

	private static final String NAME = "keyList";
	protected static final Class<? extends IValue> RETURN_TYPE = ListValue.class;
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();

	static {
		DEFINED_TYPES.add(MapValue.class);
	}

	public KeyListMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		ListValue keyList = new ListValue();
		IMapValue mapSymbol = (IMapValue) arguments.get(0);
		Set<String> keySet = mapSymbol.getData().getKeySet();
		for(String key : keySet) {
			keyList.addNewStringSymbol(key);
		}
		return keyList;
	}

}
