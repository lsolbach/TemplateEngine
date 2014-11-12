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

public class TokenFit {

	public int index;
	public String type;
	public String data;

	public TokenFit(int index, String type, String data) {
		super();
		this.index = index;
		this.type = type;
		this.data = data;
	}

}
