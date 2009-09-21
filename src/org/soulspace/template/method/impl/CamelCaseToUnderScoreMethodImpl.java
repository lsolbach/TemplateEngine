package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.method.IMethod;
import org.soulspace.template.value.IValue;
import org.soulspace.template.value.impl.StringValue;

public class CamelCaseToUnderScoreMethodImpl extends AbstractMethod
		implements IMethod {

	private static final String NAME = "camelCaseToUnderScore";
	protected static final List<Class<? extends IValue>> DEFINED_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final List<Class<? extends IValue>> ARGUMENT_TYPES = new ArrayList<Class<? extends IValue>>();
	protected static final Class<? extends IValue> RETURN_TYPE = StringValue.class;

	static {
		DEFINED_TYPES.add(StringValue.class);
	}
	
	public CamelCaseToUnderScoreMethodImpl() {
		super();
		this.name = NAME;
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
	}
	
	@Override
	protected IValue doEvaluation(List<IValue> arguments) {
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
			return new StringValue(sb.toString());
		} else {
			return arguments.get(0);			
		}
	}

}
