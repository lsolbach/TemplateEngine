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

public class TmplPartitionScanner extends RuleBasedPartitionScanner {
	public final static String TMPL_COMMENT = "__tmpl_comment";
	public final static String TMPL_CODE = "__tmpl_code";

	public TmplPartitionScanner() {

		IToken tmplComment = new Token(TMPL_COMMENT);
		IToken code = new Token(TMPL_CODE);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<?!--", "--?>", tmplComment);
		rules[1] = new CodeRule(code);

		setPredicateRules(rules);
	}
}
