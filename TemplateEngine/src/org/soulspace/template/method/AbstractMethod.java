/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.method;

import java.util.Collections;
import java.util.List;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.value.Value;

public abstract class AbstractMethod implements Method {

	protected String name;
	protected List<Class<? extends Value>> definedTypes;
	protected List<Class<? extends Value>> argumentTypes;
	protected Class<? extends Value> returnType;
	
	public boolean definedFor(Value symbol) {
		return definedTypes.contains(symbol.getClass());
	}

	public List<Class<? extends Value>> getArgumentTypes() {
		return Collections.unmodifiableList(argumentTypes);
	}

	public List<Class<? extends Value>> getDefinedTypes() {
		return Collections.unmodifiableList(definedTypes);
	}

	public String getName() {
		return name;
	}

	public Class<? extends Value> getReturnType() {
		return returnType;
	}

	public boolean validateTypes(List<Value> arguments) {
		if(arguments.size() != argumentTypes.size() + 1) {
			throw new GenerateException("Argument count mismatch on method " + getName());			
		}
		if(false) {
			// FIXME implement generic type checking
			throw new GenerateException("Type mismatch on method " + getName());
		}
		return true;
	}

	public Value evaluate(List<Value> arguments) {
		validateTypes(arguments);
		return doEvaluation(arguments);
	}
	
	protected abstract Value doEvaluation(List<Value> arguments);
}
