package org.soulspace.template;

import junit.framework.TestCase;

import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.tokenizer.IToken;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.TokenType;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;

public class TokenizerTest extends TestCase {

	TemplateEngineImpl te = null;
	ITokenList tl = null;
	Tokenizer t = null;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		te = new TemplateEngineImpl();
		t = new TokenizerImpl();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		te = null;
		t = null;
		super.tearDown();
	}

	public void testTokMultiLine() {
		try {
			tl = t.tokenize("<html>\n" + "<head>\n" + "</head>\n" + "<body>\n"
					+ "</body>\n" + "</html>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testKewordPrefix1() {
		try {
			tl = t.tokenize("<?string stringVar?>");
			assertEquals("Tokenlist has 2 Tokens", 2, tl.size());
			assertEquals(TokenType.DECLARATION, tl.getToken().getType());
			assertEquals(TokenType.IDENTIFIER, tl.getNextToken().getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testKewordPrefix2() {
		try {
			tl = t.tokenize("<?numeric equal?>");
			assertEquals("Tokenlist has 2 Tokens", 2, tl.size());
			assertEquals(TokenType.DECLARATION, tl.getToken().getType());
			assertEquals(TokenType.IDENTIFIER, tl.getNextToken().getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testKewordPrefix3() {
		try {
			tl = t.tokenize("<?string elseKling?>");
			assertEquals("Tokenlist has 2 Tokens", 2, tl.size());
			assertEquals(TokenType.DECLARATION, tl.getToken().getType());
			assertEquals(TokenType.IDENTIFIER, tl.getNextToken().getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testTokMethodCall() {
		try {
			tl = t.tokenize("<?call('Hello world')?>");
			assertEquals("Tokenlist has 4 Tokens", 4, tl.size());
			assertEquals(TokenType.IDENTIFIER, tl.getToken().getType());
			assertEquals(TokenType.PAREN_LEFT, tl.getNextToken().getType());
			assertEquals(TokenType.STRING_CONST, tl.getNextToken().getType());
			assertEquals(TokenType.PAREN_RIGHT, tl.getNextToken().getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testTokTypeMethodCall() {
		try {
			tl = t.tokenize("<?name.toUpper()?>");
			assertEquals("Tokenlist has 5 Tokens", 5, tl.size());
			assertEquals(TokenType.IDENTIFIER, tl.getToken().getType());
			assertEquals(TokenType.TYPE_METHOD_CALL, tl.getNextToken()
					.getType());
			assertEquals(TokenType.IDENTIFIER, tl.getNextToken().getType());
			assertEquals(TokenType.PAREN_LEFT, tl.getNextToken().getType());
			assertEquals(TokenType.PAREN_RIGHT, tl.getNextToken().getType());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testTokStringConst() {
		try {
			tl = t.tokenize("<?'\\''?>");
			assertEquals("Tokenlist has 1 Tokens", 1, tl.size());
			assertEquals("Token is string constant", TokenType.STRING_CONST, tl
					.getToken().getType());
			assertEquals("Token data is '", "'", tl.getToken().getData());

			tl = t.tokenize("<?'begin'?>");
			assertEquals("Tokenlist has 1 Tokens", 1, tl.size());
			assertEquals("Token is string constant", TokenType.STRING_CONST, tl
					.getToken().getType());
			assertEquals("Token data is 'begin'", "begin", tl.getToken()
					.getData());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		}
	}

	public void testTokenLines() {
		tl = t.tokenize("<html>\n" + "<head>\n" + "</head>\n" + "\n"
				+ "<body>\n" + "<?\n" + "foreach item <- list {\n" + "\n"
				+ "  item:Name\n" + "}\n" + "?>\n" + "</body>\n" + "</html>");
		IToken tk;
		tk = tl.getToken();
		assertEquals("Line 1, Token " + tk, 1, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 7, Token " + tk, 7, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 7, Token " + tk, 7, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 7, Token " + tk, 7, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 7, Token " + tk, 7, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 7, Token " + tk, 7, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 8, Token " + tk, 9, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 8, Token " + tk, 9, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 8, Token " + tk, 9, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 9, Token " + tk, 10, tk.getLine());
		tk = tl.getNextToken();
		assertEquals("Line 10, Token " + tk, 11, tk.getLine());

		tl = t.tokenize("<?!--multiline\n" + "comment\n" + "test--?>\n"
				+ "<html>\n" + "<head>\n" + "</head>\n" + "\n" + "<body>\n"
				+ "<?\n" + "foreach item <- list {?>\n" + "  <?item:Name?>\n"
				+ "<?}\n" + "?>\n" + "</body>\n" + "</html>");

	}

}
