/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.tokenizer;

import org.soulspace.template.exception.SyntaxException;

public interface TokenList {

	void setCurrentLine(int line);

	void incCurrentLines(int lines);

	void incCurrentLine();

	int getCurrentLine();

	/**
	 * @return the template
	 */
	String getTemplate();

	/**
	 * @param template the template to set
	 */
	void setTemplate(String template);

	/**
	 * Add a new token.
	 * @param type
	 */
	void addToken(TokenType type);

	/**
	 * Add a new token
	 * @param type
	 * @param data
	 */
	void addToken(TokenType type, String data);

	/**
	 * Return the number of tokens in the token list
	 * @return
	 */
	int size();

	/**
	 * Return the token, the list pointer points to.
	 * @return TokenImpl
	 */
	Token getToken();

	/**
	 * Increment the list pointer and return the token at that position
	 * @return TokenImpl
	 */
	Token getNextToken();

	/**
	 * Increment the list pointer
	 */
	void skipToken();

	/**
	 * Check if there's a token at the position of the list pointer
	 * @return boolean
	 */
	boolean hasToken();

	/**
	 * Check if there's a token at the next position of the list pointer.
	 * @return boolean
	 */
	boolean hasNextToken();

	/**
	 * Return the token at the next list position
	 * without incrementing the list pointer.
	 * @return TokenType
	 */
	Token lookUpToken();

	Token lookUpLastToken();

	/**
	 * Return the TokenImpl with lookahead k without incrementing the list pointer.
	 * @param k
	 * @return
	 */
	Token lookUpToken(int k);

	/**
	 * 
	 * @return
	 */
	TokenType lookUpTokenType();

	boolean checkType(TokenType type);

	boolean checkNextType(TokenType type);

	void validateType(TokenType type) throws SyntaxException;

	Object[] toArray();

}