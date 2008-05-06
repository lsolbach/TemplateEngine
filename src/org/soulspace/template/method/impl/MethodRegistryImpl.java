package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.IMethod;
import org.soulspace.template.util.ClassLoaderUtils;

public class MethodRegistryImpl {

	Map<String, IMethod> registry = new HashMap<String, IMethod>();

	public MethodRegistryImpl() {
		List<Class> classList = ClassLoaderUtils.getImplementationsInPackage("org.soulspace.template.method.impl", IMethod.class);
		IMethod method;
		for(Class methodClass : classList) {
				try {
					method = (IMethod) methodClass.newInstance();
					registry.put(method.getName(), method);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}	
	
	public IMethod lookup(String name) {
		return registry.get(name);
	}
}
