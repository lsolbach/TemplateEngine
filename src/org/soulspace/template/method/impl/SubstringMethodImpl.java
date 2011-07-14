package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class SubstringMethodImpl extends AbstractMethod {

	private static final String NAME = "substring";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
		ARGUMENT_TYPES.add(NumericValue.class);
		ARGUMENT_TYPES.add(NumericValue.class);
	}
	
	public SubstringMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		String value = arguments.get(0).evaluate();
		Long indexLow = ((NumericValue) arguments.get(1)).asLong();
		if(arguments.size() == 3) {
			Long indexHigh = ((NumericValue) arguments.get(2)).asLong();
			if(indexLow > indexHigh) {
				return new StringValueImpl("");
			}			
			return new StringValueImpl(value.substring(indexLow.intValue(), indexHigh.intValue()));
		} else {
			return new StringValueImpl(value.substring(indexLow.intValue()));			
		}
	}

}
