package org.soulspace.template;

import junit.framework.TestCase;

import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.TokenType;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.TokenizerImpl;
import org.soulspace.template.tokenizer.UnknownTokenException;

public class TokenizerTest extends TestCase {

  TemplateEngineImpl te = null;
  TokenList tl = null;
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

  public void testTokMethodCall() {
    try {
      tl = t.tokenize("<?call('Hello world')?>");
      assertEquals("Tokenlist has 4 Tokens", 4, tl.size());
      assertEquals(TokenType.IDENTIFIER, tl.getToken().getType());
      assertEquals(TokenType.PAREN_LEFT, tl.getNextToken().getType());
      assertEquals(TokenType.STRING_CONST, tl.getNextToken().getType());
      assertEquals(TokenType.PAREN_RIGHT, tl.getNextToken().getType());
    } catch(UnknownTokenException e) {
      e.printStackTrace(); 
    }
  }
  
  public void testTokTypeMethodCall() {
    try {
      tl = t.tokenize("<?name.toUpper()?>");
      assertEquals("Tokenlist has 5 Tokens", 5, tl.size());
      assertEquals(TokenType.IDENTIFIER, tl.getToken().getType());
      assertEquals(TokenType.TYPE_METHOD_CALL, tl.getNextToken().getType());
      assertEquals(TokenType.IDENTIFIER, tl.getNextToken().getType());
      assertEquals(TokenType.PAREN_LEFT, tl.getNextToken().getType());
      assertEquals(TokenType.PAREN_RIGHT, tl.getNextToken().getType());
    } catch(UnknownTokenException e) {
      e.printStackTrace(); 
    }
  }
}
