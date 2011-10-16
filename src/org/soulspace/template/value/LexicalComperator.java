package org.soulspace.template.value;

import java.io.Serializable;
import java.util.Comparator;

public class LexicalComperator implements Comparator<Value>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(Value arg0, Value arg1) {
		return arg0.asString().compareTo(arg1.asString());
	}
}
