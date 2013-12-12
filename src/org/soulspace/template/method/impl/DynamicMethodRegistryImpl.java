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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.AbstractMethodRegistry;
import org.soulspace.template.method.Method;
import org.soulspace.template.method.MethodRegistry;
import org.soulspace.template.util.ClassLoaderUtils;

public class DynamicMethodRegistryImpl extends AbstractMethodRegistry implements MethodRegistry {

	/**
	 * Constructs a MethodRegistry for the default package.
	 */
	public DynamicMethodRegistryImpl() {
		this("org.soulspace.template.method.impl");
	}

	/**
	 * Constructs a MethodRegistry for the given package.
	 * 
	 * @param packageName The name of the package.
	 */
	public DynamicMethodRegistryImpl(String packageName) {
		registerPackage(packageName);
	}

	/**
	 * Constructs a MethodRegistry for the given package.
	 * 
	 * @param packageNames The names of the packages.
	 */
	public DynamicMethodRegistryImpl(String... packageNames) {
		for(String packageName : packageNames) {
			registerPackage(packageName);
		}
	}

	/**
	 * Register the method classes in the given package.
	 * 
	 * @param packageName The name of the package.
	 */
	@SuppressWarnings("rawtypes")
	public final void registerPackage(String packageName) {
		//System.out.println("registering methods from package " + packageName + "...");
		List<Class> classList = ClassLoaderUtils.getImplementationsInPackage(
				packageName, Method.class);
		for (Class methodClass : classList) {
			register(methodClass.getName());
		}
	}
}
