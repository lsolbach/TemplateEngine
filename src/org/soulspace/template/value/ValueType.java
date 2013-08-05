/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.value;

import org.soulspace.template.exception.GenerateException;

/**
 * @author soulman
 * 
 * Typesafe enumeration of symbol types. Version: $Id
 */
public class ValueType {

	public final static ValueType ANY = new ValueType("any");
	public final static ValueType STRING = new ValueType("string");
	public final static ValueType NUMERIC = new ValueType("numeric");
	public final static ValueType LIST = new ValueType("list");
	public final static ValueType MAP = new ValueType("map");
	public final static ValueType METHOD = new ValueType("method");
	// public final static ValueType EXPRESSION = new ValueType("expression");

	private final String name;

	private ValueType(String name) {
		this.name = name;
	}

	public static ValueType valueOf(String typeName) {
		if (typeName.equals(STRING.getName())) {
			return STRING;
		} else if (typeName.equals(NUMERIC.getName())) {
			return NUMERIC;
		} else if (typeName.equals(LIST.getName())) {
			return LIST;
		} else if (typeName.equals(MAP.getName())) {
			return MAP;
		} else if (typeName.equals(METHOD.getName())) {
			return METHOD;
		} else if (typeName.equals(ANY.getName())) {
			return ANY;
		} else {
			throw new GenerateException("Invalid symbol type " + typeName);
		}
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
