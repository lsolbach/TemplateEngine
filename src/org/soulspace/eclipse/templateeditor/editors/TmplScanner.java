/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.eclipse.templateeditor.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class TmplScanner extends RuleBasedScanner {

	public TmplScanner(ColorManager manager) {

		IRule[] rules = new IRule[1];
		// Add generic whitespace rule.
		rules[0] = new WhitespaceRule(new TmplWhitespaceDetector());

		setRules(rules);
	}
}
