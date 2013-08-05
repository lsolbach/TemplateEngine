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

import org.soulspace.template.method.Method;
import org.soulspace.template.method.MethodRegistry;

public class MethodRegistryImpl {

	// FIXME don't initialize registry statically here
	private static MethodRegistry methodRegistry = new StaticMethodRegistryImpl();

	public static void setMethodRegisty(MethodRegistry aMethodRegistry) {
		methodRegistry = aMethodRegistry;
	}

	public static Method lookup(String methodClassName) {
		return methodRegistry.lookup(methodClassName);
	}

	public static void register(String methodClassName) {
		methodRegistry.register(methodClassName);
	}
	
}
