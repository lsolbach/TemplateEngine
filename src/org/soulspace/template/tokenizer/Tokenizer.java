/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.tokenizer;


public interface Tokenizer {

  TokenList tokenize(String input) throws UnknownTokenException;
}
