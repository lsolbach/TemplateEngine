package org.soulspace.template.method.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.Method;
import org.soulspace.template.method.MethodRegistry;

public class StaticMethodRegistryImpl implements MethodRegistry {
	static List<String> methodClassList = new ArrayList<String>();
	static {
		methodClassList.add("org.soulspace.template.method.impl.FirstLowerMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.FirstUpperMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ToLowerMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ToUpperMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.EndsWithMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.StartsWithMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.MatchesMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.SplitMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.CompareMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.IndexOfMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.SubstringMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ReplaceMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.CamelCaseToUnderScoreMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.Utf8ToIsoLatinMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.IsoLatinToUtf8MethodImpl");

		methodClassList.add("org.soulspace.template.method.impl.SizeMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.HasNextElementMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.GetElementMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ElementIndexMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ListAddMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ListClearMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.ReverseListMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.MapPutMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.MapRemoveMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.MapClearMethodImpl");
		methodClassList.add("org.soulspace.template.method.impl.KeyListMethodImpl");

		methodClassList.add("org.soulspace.template.method.impl.TypeMethodImpl");
		
		methodClassList.add("org.soulspace.template.method.impl.DebugMethodImpl");
	}
	
	String name = "";
	String className = "";
	Map<String, Method> registry = new HashMap<String, Method>();

	public StaticMethodRegistryImpl() {
		this(methodClassList);
	}
	
	public StaticMethodRegistryImpl(List<String> methodClassList) {
		for(String methodClassName : methodClassList) {
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
