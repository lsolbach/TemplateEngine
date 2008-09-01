/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.tokenizer;

import org.soulspace.template.exception.UnknownTokenException;


public interface Tokenizer {

	ITokenList createTokenList();
  ITokenList tokenize(String input) throws UnknownTokenException;
  ITokenList tokenize(ITokenList tokenList, String input) throws UnknownTokenException;
}
