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

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;

import fit.RowFixture;

public class Tokenlist extends RowFixture {

	public Tokenlist() {
		super();
	}

	@Override
	public Object[] query() throws Exception {
		Tokenizer tokenizer = new TokenizerImpl();
		List<TokenFit> list = new ArrayList<TokenFit>();
		TokenList tl = tokenizer.tokenize(args[0]);
		int i = 0;
		while (tl.hasToken()) {
			Token t = tl.getToken();
			TokenFit tf = new TokenFit(i, t.getType().toString(), t.getData());
			list.add(tf);
			tl.getNextToken();
			i++;
		}
		return list.toArray();
	}

	@Override
	public Class getTargetClass() {
		return TokenFit.class;
	}

}
