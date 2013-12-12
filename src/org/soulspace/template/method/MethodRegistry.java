/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.method;

public interface MethodRegistry {

	/**
	 * Register a method class by name.
	 * 
	 * @param methodClassName The name of the class to register.
	 */
	void register(String methodClassName);

	/**
	 * Lookup a method by the methods class name.
	 * 
	 * @param methodClassName The name of the class of the method.
	 * @return The method, if it is registered by the class name.
	 */
	Method lookup(String methodClassName);
}
