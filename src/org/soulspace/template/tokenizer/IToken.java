package org.soulspace.template.tokenizer;

import org.soulspace.template.exception.SyntaxException;

public interface IToken {

	/**
	 * Returns the type of the token.
	 * @return TokenType
	 */
	TokenType getType();

	/**
	 * Returns the data of the token.
	 * @return String
	 */
	String getData();

	void setData(String value);

	int getLine();

	void setLine(int line);

	String getTemplate();

	boolean validateType(TokenType type) throws SyntaxException;

	boolean checkType(TokenType type);

}