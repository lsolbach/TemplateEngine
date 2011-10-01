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
