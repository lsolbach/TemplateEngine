package org.soulspace.template.method.impl;

import org.soulspace.template.method.IMethod;
import org.soulspace.template.method.IMethodRegistry;

public class MethodRegistryImpl {

	// FIXME don't initialize registry statically here
	private static IMethodRegistry methodRegistry = new StaticMethodRegistryImpl();

	public static void setMethodRegisty(IMethodRegistry aMethodRegistry) {
		methodRegistry = aMethodRegistry;
	}

	public static IMethod lookup(String methodClassName) {
		return methodRegistry.lookup(methodClassName);
	}

	public static void register(String methodClassName) {
		methodRegistry.register(methodClassName);
	}
	
}
