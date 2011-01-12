package org.soulspace.template.method;

public interface MethodRegistry {
	Method lookup(String methodClassName);
	void register(String methodClassName);
}
