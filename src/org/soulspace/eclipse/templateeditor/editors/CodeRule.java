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
		// TODO Auto-generated constructor stub
	}

	public CodeRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter) {
		super(startSequence, endSequence, token, escapeCharacter);
		// TODO Auto-generated constructor stub
	}

	public CodeRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter, boolean breaksOnEOF) {
		super(startSequence, endSequence, token, escapeCharacter, breaksOnEOF);
		// TODO Auto-generated constructor stub
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
