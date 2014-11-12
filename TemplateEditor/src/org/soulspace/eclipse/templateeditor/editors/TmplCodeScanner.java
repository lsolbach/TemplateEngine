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

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class TmplCodeScanner extends RuleBasedScanner {

	public TmplCodeScanner(ColorManager manager) {
		IToken string =
			new Token(
				new TextAttribute(manager.getColor(ITmplColorConstants.STRING)));
		IToken number =
			new Token(
				new TextAttribute(manager.getColor(ITmplColorConstants.NUMBER)));
		IToken code_comment =
			new Token(
				new TextAttribute(manager.getColor(ITmplColorConstants.CODE_COMMENT)));
		
		IRule[] rules = new IRule[5];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add a rule for numbers
		rules[2] = new NumberRule(number);
		// Add a rule for code comments
		rules[3] = new MultiLineRule("/*", "*/", code_comment);
		// Add generic whitespace rule.
		rules[4] = new WhitespaceRule(new TmplWhitespaceDetector());

		setRules(rules);
	}
}
