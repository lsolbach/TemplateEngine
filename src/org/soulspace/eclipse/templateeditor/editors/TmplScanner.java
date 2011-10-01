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
