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
