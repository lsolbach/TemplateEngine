/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package fittest.tokenizer;

import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;

import fit.ColumnFixture;

public class Tokenizer extends ColumnFixture {

	org.soulspace.template.tokenizer.Tokenizer TOKENIZER = new TokenizerImpl();
	TokenList TOKEN_LIST = TOKENIZER.createTokenList();

	public String input;

	public Tokenizer() {
		super();
	}

	public boolean tokenizeInput() {
		try {
			TOKEN_LIST = TOKENIZER.tokenize(input);
		} catch (UnknownTokenException e) {
			e.printStackTrace();
			return false;
		}
		if (TOKEN_LIST.size() > 0) {
			return true;
		}
		return false;
	}

}
