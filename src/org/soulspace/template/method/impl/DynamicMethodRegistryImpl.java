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

import org.soulspace.template.method.Method;
import org.soulspace.template.method.MethodRegistry;
import org.soulspace.template.util.ClassLoaderUtils;

public class DynamicMethodRegistryImpl implements MethodRegistry {

	Map<String, Method> registry = new HashMap<String, Method>();

	public DynamicMethodRegistryImpl() {
		System.out.println("registering Methods...");
		registerPackage("org.soulspace.template.method.impl");
	}

	@SuppressWarnings("rawtypes")
	public final void registerPackage(String packageName) {
		List<Class> classList = ClassLoaderUtils.getImplementationsInPackage(
				packageName, Method.class);
		Method method;
		for (Class methodClass : classList) {
			try {
				method = (Method) methodClass.newInstance();
				registry.put(method.getName(), method);
				System.out.println("registered method " + method.getName());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void register(String methodClassName) {
		try {
			Method method = (Method) Class.forName(methodClassName).newInstance();
			registry.put(method.getName(), method);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Method lookup(String name) {
		return registry.get(name);
	}
}
