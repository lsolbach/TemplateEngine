/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.tokenizer;

import org.soulspace.template.exception.UnknownTokenException;

public interface Tokenizer {

	TokenList createTokenList();

	TokenList tokenize(String input) throws UnknownTokenException;

	TokenList tokenize(TokenList tokenList, String input)
			throws UnknownTokenException;
}
