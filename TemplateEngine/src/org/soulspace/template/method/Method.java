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

import java.util.List;

import org.soulspace.template.value.Value;

public interface Method {

	String getName();
	List<Class<? extends Value>> getArgumentTypes();
	Class<? extends Value> getReturnType();
	List<Class<? extends Value>> getDefinedTypes();	
	boolean definedFor(Value symbol);
	Value evaluate(List<Value> arguments);

}
