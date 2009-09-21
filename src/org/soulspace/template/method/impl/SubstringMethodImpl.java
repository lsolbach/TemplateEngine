package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.IMethod;
import org.soulspace.template.value.IMapValue;
import org.soulspace.template.value.INumericValue;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class SubstringMethodImpl extends AbstractMethod {

	private static final String NAME = "substring";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
		ARGUMENT_TYPES.add(INumericValue.class);
		ARGUMENT_TYPES.add(INumericValue.class);
	}
	
	public SubstringMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
		String value = arguments.get(0).evaluate();
		Long indexLow = ((INumericValue) arguments.get(1)).asLong();
		if(arguments.size() == 3) {
			Long indexHigh = ((INumericValue) arguments.get(2)).asLong();
			if(indexLow > indexHigh) {
				return new StringValue("");
			}			
			return new StringValue(value.substring(indexLow.intValue(), indexHigh.intValue()));
		} else {
			return new StringValue(value.substring(indexLow.intValue()));			
		}
	}

}
