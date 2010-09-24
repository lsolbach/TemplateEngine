package org.soulspace.template.method;

public interface IMethodRegistry {
	IMethod lookup(String methodClassName);
	void register(String methodClassName);
}
