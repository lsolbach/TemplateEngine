package org.soulspace.template.value;

import java.util.Comparator;

public class LexicalComperator implements Comparator<Value> {

	public int compare(Value arg0, Value arg1) {
		return arg0.asString().compareTo(arg1.asString());
	}
}
