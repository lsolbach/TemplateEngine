package org.soulspace.template.method;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMethodRegistry implements MethodRegistry {

	protected Map<String, Method> registry = new HashMap<String, Method>();

	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	public Method lookup(String name) {
		return registry.get(name);
	}
}
