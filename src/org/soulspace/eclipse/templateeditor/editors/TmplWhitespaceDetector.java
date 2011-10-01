package org.soulspace.eclipse.templateeditor.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class TmplWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
