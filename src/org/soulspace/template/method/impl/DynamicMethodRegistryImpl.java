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
