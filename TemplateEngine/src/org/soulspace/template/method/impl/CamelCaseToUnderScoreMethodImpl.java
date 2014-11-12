/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
				Character ch = camelCase.charAt(i);
				if(Character.isLetter(ch) && Character.isUpperCase(ch)) {
					if(i > 0 && !(Character.isUpperCase(camelCase.charAt(i - 1)))) {
						sb.append("_");
					}
					sb.append(Character.toLowerCase(ch));
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