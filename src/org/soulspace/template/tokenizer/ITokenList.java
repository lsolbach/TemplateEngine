package org.soulspace.template.tokenizer;

import org.soulspace.template.exception.SyntaxException;

public interface ITokenList {

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
	 * @return Token
	 */
	IToken getToken();

	/**
	 * Increment the list pointer and return the token at that position
	 * @return Token
	 */
	IToken getNextToken();

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
	IToken lookUpToken();

	IToken lookUpLastToken();

	/**
	 * Return the Token with lookahead k without incrementing the list pointer.
	 * @param k
	 * @return
	 */
	IToken lookUpToken(int k);

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