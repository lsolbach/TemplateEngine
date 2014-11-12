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

public interface Token {

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