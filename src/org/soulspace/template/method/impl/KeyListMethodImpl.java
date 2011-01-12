package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MapValueImpl;

public class KeyListMethodImpl extends AbstractMethod {

	private static final String NAME = "keyList";
	protected static final Class<? extends Value> RETURN_TYPE = ListValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(MapValueImpl.class);
	}

	public KeyListMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		ListValueImpl keyList = new ListValueImpl();
		MapValue mapSymbol = (MapValue) arguments.get(0);
		Set<String> keySet = mapSymbol.getData().getKeySet();
		for(String key : keySet) {
			keyList.addNewStringValue(key);
		}
		return keyList;
	}

}
