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

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class CodeRule extends MultiLineRule {

	public CodeRule(IToken token) {
		super("<?", "?>", token);
	}

	public CodeRule(String startSequence, String endSequence, IToken token) {
		super(startSequence, endSequence, token);
	}

	public CodeRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter) {
		super(startSequence, endSequence, token, escapeCharacter);
	}

	public CodeRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter, boolean breaksOnEOF) {
		super(startSequence, endSequence, token, escapeCharacter, breaksOnEOF);
	}

//	protected boolean sequenceDetected(ICharacterScanner scanner,
//			char[] sequence, boolean eofAllowed) {
//		int c = scanner.read();
//		if (sequence[0] == '<') {
//			if (c == '?') {
//				// processing instruction - abort
//				scanner.unread();
//				return false;
//			}
//			if (c == '!') {
//				scanner.unread();
//				// comment - abort
//				return false;
//			}
//		} else if (sequence[0] == '>') {
//			scanner.unread();
//		}
//		return super.sequenceDetected(scanner, sequence, eofAllowed);
//	}

}
