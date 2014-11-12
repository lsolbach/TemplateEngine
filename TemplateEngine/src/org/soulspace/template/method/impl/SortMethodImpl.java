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
import java.util.Collections;
import java.util.List;

import org.soulspace.template.method.AbstractMethod;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MethodValue;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.MethodValueImpl;

public class SortMethodImpl extends AbstractMethod {

	private static final String NAME = "add";
	protected static final Class<? extends Value> RETURN_TYPE = ListValueImpl.class;
	protected static final List<Class<? extends Value>> DEFINED_TYPES = new ArrayList<Class<? extends Value>>();
	protected static final List<Class<? extends Value>> ARGUMENT_TYPES = new ArrayList<Class<? extends Value>>();

	static {
		DEFINED_TYPES.add(ListValue.class);
		ARGUMENT_TYPES.add(MethodValue.class);
	}

	public SortMethodImpl() {
		super();
		this.returnType = RETURN_TYPE;
		this.argumentTypes = ARGUMENT_TYPES;
		this.definedTypes = DEFINED_TYPES;
		this.name = NAME;
	}

	@Override
	protected Value doEvaluation(List<Value> arguments) {
		ListValueImpl list = (ListValueImpl) arguments.get(0);
		MethodValueImpl method;
		List<Value> sortedList = new ArrayList<Value>();
		List<IndexValue> idxList = new ArrayList<IndexValue>();
		if(arguments.size() > 1) {
			method = (MethodValueImpl) arguments.get(1);
			for(int i = 0; i < list.size(); i++) {
				List<Value> valueList = new ArrayList<Value>();
				valueList.add(list.getData().get(i));
				// FIXME call method on value
				// idxList.add(new IndexValue(i, methodNode.generateSymbol(environment, returnNode, valueList).asString()));
			}
		} else {
			for(int i = 0; i < list.size(); i++) {
				idxList.add(new IndexValue(i, list.getData().get(i).asString()));
			}
		}
		Collections.sort(idxList);
		for(IndexValue idxValue : idxList) {
			sortedList.add(list.getData().get(idxValue.index));
		}
		ListValue result = new ListValueImpl(sortedList);
		return result;
	}

	class IndexValue implements Comparable<IndexValue> {
		String value;
		int index;

		public IndexValue(int index, String value) {
			this.index = index;
			this.value = value;
		}

		public int compareTo(IndexValue arg0) {
			return value.compareTo(arg0.value);
		}
	}
}
