package org.soulspace.template.method.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.IMethod;
import org.soulspace.template.method.IMethodRegistry;
import org.soulspace.template.util.ClassLoaderUtils;

public class DynamicMethodRegistryImpl implements IMethodRegistry {

	Map<String, IMethod> registry = new HashMap<String, IMethod>();

	public DynamicMethodRegistryImpl() {
		System.out.println("registering Methods...");
		registerPackage("org.soulspace.template.method.impl");
	}

	public final void registerPackage(String packageName) {
		List<Class> classList = ClassLoaderUtils.getImplementationsInPackage(
				packageName, IMethod.class);
		IMethod method;
		for (Class methodClass : classList) {
			try {
				method = (IMethod) methodClass.newInstance();
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
			IMethod method = (IMethod) Class.forName(methodClassName).newInstance();
			registry.put(method.getName(), method);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public IMethod lookup(String name) {
		return registry.get(name);
	}
}
