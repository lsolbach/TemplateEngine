package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.Method;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.StringValueImpl;

public class CamelCaseToUnderScoreMethodImpl extends AbstractMethod
		implements Method {

	private static final String NAME = "camelCaseToUnderScore";
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final Class<? extends Value> RETURN_TYPE = StringValueImpl.class;

	static {
		DEFINED_TYPES.add(StringValueImpl.class);
	}
	
	public CamelCaseToUnderScoreMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected Value doEvaluation(List<Value> arguments) {
		String camelCase = arguments.get(0).evaluate();
		
		if(camelCase != null && camelCase.length() > 0) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < camelCase.length(); i++) {
				String ch = camelCase.substring(i, i + 1);
				if(ch.equals(ch.toUpperCase())) {
					if(i != 0) {
						sb.append("_");
					}
					sb.append(ch.toLowerCase());
				} else {
					sb.append(ch);
				}
			}
			return new StringValueImpl(sb.toString());
		} else {
			return arguments.get(0);			
		}
	}

}
