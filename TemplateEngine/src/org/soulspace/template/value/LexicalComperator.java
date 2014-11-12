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

import java.io.Serializable;
import java.util.Comparator;

public class LexicalComperator implements Comparator<Value>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(Value arg0, Value arg1) {
		return arg0.asString().compareTo(arg1.asString());
	}
}
