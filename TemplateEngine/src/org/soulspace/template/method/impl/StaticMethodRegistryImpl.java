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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.template.method.AbstractMethodRegistry;
import org.soulspace.template.method.Method;
import org.soulspace.template.method.MethodRegistry;

public class StaticMethodRegistryImpl extends AbstractMethodRegistry implements MethodRegistry {

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
		methodClassList.add("org.soulspace.template.method.impl.UuidMethodImpl");

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
	
	/**
	 * Constructs a MethodRegistry with the statically configured methods.
	 */
	public StaticMethodRegistryImpl() {
		this(methodClassList);
	}
	
	/**
	 * Constructs a MethodRegistry for the list of method class names.
	 * @param methodClassList The list of method class names.
	 */
	public StaticMethodRegistryImpl(List<String> methodClassList) {
		for(String methodClassName : methodClassList) {
			register(methodClassName);
		}
	}
	
}