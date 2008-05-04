package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.IMethod;

public class MethodRegistryImpl {

	static List<String> methodClassList = new ArrayList<String>();
	static {
		methodClassList.add("org.soulspace.template.method.impl.FirstLowerMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.FirstUpperMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ToLowerMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ToUpperMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.StartsWithMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.SplitMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ElementMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ElementIndexMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.SizeMethodImpl");
	}
	
	String name = "";
	String className = "";
	Map<String, IMethod> registry = new HashMap<String, IMethod>();

	public MethodRegistryImpl() {
		this(methodClassList);
	}
	
	public MethodRegistryImpl(List<String> methodClassList) {
		for(String methodClassName : methodClassList) {
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
	}	
	
	public IMethod lookup(String name) {
		return registry.get(name);
	}
}
