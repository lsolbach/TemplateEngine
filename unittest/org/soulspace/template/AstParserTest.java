package org.soulspace.template;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.impl.AstGeneratorImpl;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.impl.SymbolTableImpl;

import junit.framework.TestCase;

public class AstParserTest extends TestCase {

	TemplateEngineImpl te = null;
	TokenList tl = null;
	Tokenizer t = null;
	AstParserImpl p = null;
	AstGeneratorImpl g = null;
	SymbolTable st = null;
	AstNode root = null;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		te = new TemplateEngineImpl();
		t = new TokenizerImpl();
		p = new AstParserImpl();
		g = new AstGeneratorImpl();
		st = new SymbolTableImpl();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		te = null;
		t = null;
		p = null;
		g = null;
		st = null;
		root = null;
		super.tearDown();
	}

	public void testComments() {
		tl = t.tokenize("<?!-- just a single comment --?>");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has no childs", 0, root.getChildNodes().size());

		tl = t.tokenize("template text" + "<?!-- a comment --?>"
				+ "more template text");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has one child", 1, root.getChildNodes().size());
		assertEquals("Expecting TEXT", AstNodeType.TEXT, root.getChild(0)
				.getType());

		tl = t
				.tokenize("<?numeric templateCode?><?!-- a comment --?><?templateCode = 0?>");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has 2 childs", 2, root.getChildNodes().size());
		assertEquals("Expecting DECLARATION", AstNodeType.DECLARATION, root
				.getChild(0).getType());
		assertEquals("Expecting ASSIGN", AstNodeType.ASSIGN, root.getChild(1)
				.getType());

		tl = t
				.tokenize("<?numeric templateCode<?!-- a comment --?>templateCode = 0?>");
		root = p.parseTerm(tl, null, false);
		// FIXME add assertions

		tl = t.tokenize("<?!--numeric templateCode templateCode = 0--?>");
		root = p.parseTerm(tl, null, false);
		// FIXME add assertions

	}

	public void testCodeComments() {
		// code comment in code
		tl = t.tokenize("<?/* just a single comment */?>");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has no childs", 0, root.getChildNodes().size());

		// code comment in text
		tl = t.tokenize("template text " + "/* a code comment */"
				+ " more template text");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has one child", 1, root.getChildNodes().size());
		assertEquals("Expecting TEXT", AstNodeType.TEXT, root.getChild(0)
				.getType());

		// 
		tl = t.tokenize("<?" + "numeric templateCode" + "/* a comment */"
				+ "templateCode = 0" + "?>");
		root = p.parseTerm(tl, null, false);
		assertEquals("AST has 2 childs", 2, root.getChildNodes().size());
		assertEquals("Expecting DECLARATION", AstNodeType.DECLARATION, root
				.getChild(0).getType());
		assertEquals("Expecting ASSIGN", AstNodeType.ASSIGN, root.getChild(1)
				.getType());

	}

	public void testDirectArrayMapAccess() {
		try {
			tl = t.tokenize("<?if(classes[1]:name eq 'Class2') { 'true' }?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting IF", AstNodeType.IF, root.getChild(0)
					.getType());
			assertEquals("AST has two children", 2, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?valueMap['expensive']?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			// assertEquals("Expecting IF", AstNodeType.IF,
			// root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

			tl = t
					.tokenize("<?taggedValue = Classes:TaggedValueMap['documentation']?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			// assertEquals("Expecting IF", AstNodeType.IF,
			// root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testDirectMapAccessStringConst() {
		try {

			tl = t.tokenize("<?valueMap['expensive']?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting IDENTIFIER", AstNodeType.IDENTIFIER, root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testDirectMapAccessStringVar() {
		try {

			tl = t.tokenize("<?valueMap[price]?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			// assertEquals("Expecting IF", AstNodeType.IF,
			// root.getChild(0).getType());
			// assertEquals("AST has two children", 2,
			// root.getChild(0).getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testTypeMethods() {
		try {
			tl = t.tokenize("<?name.toLower()?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());

			tl = t.tokenize("<?customer:name.toLower()?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}

	public void testForeach() {
		try {
			tl = t
					.tokenize("<?" + "foreach x <- xList { " + "x " + "} "
							+ "?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting FOREACH", AstNodeType.FOREACH, root
					.getChild(0).getType());
			assertEquals("AST has 3 children", 3, root.getChild(0)
					.getChildNodes().size());

			tl = t.tokenize("<?" + "foreach x|x:Name <- xList { " + "x " + "} "
					+ "?>");
			root = p.parseTerm(tl, null, false);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			assertEquals("Expecting FOREACH", AstNodeType.FOREACH, root
					.getChild(0).getType());
			assertEquals("AST has 4 children", 4, root.getChild(0)
					.getChildNodes().size());

		} catch (UnknownTokenException e) {
			e.printStackTrace();
			fail("UnknownTokenException: " + e.getMessage());
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
	}
	
	public void testMethodValues() {
		// FIXME add assertions
		try {
			tl = t.tokenize("<?" +
					"method f = string helloWorld() {" +
					"	'hello world'" +
					"}" +
					"?>");
			root = p.parse(tl);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
			fail("UnknownTokenException: " + e.getMessage());
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
		
	}

	public void testMethodLookup() {
		// method lookup is an expression?!
		// method declaration is a statement?!
		// method lookups for methods without parameters must be distinguishable from method calls
		// method lookups for methods without parameters must be distinguishable from method definitions 
		// helloWorld() // string helloWorld()
		// hello(string) // string hello(string)

		// should method lookup just take the name of the method instead of the name and the types of the parameters?
		// and derive the method according to the type of the parameters when called?
		// (that's how method calls work anyway)

		// should methods, when declared as an assignment to a method variable, be added to the global method table?

		// FIXME add assertions
		try {
			tl = t.tokenize("<?" +
					"string helloWorld() {" +
					"	'hello world'" +
					"}" +
					"method f = helloWorld" +
					"f()" +
					"?>");
			root = p.parse(tl);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
			tl = t.tokenize("<?" +
					"string hello(string name) {" +
					"	'hello ' + name" +
					"}" +
					"method f = hello" +
					"f('world')" +
					"?>");
			root = p.parse(tl);
			assertEquals("AST has one child", 1, root.getChildNodes().size());
		} catch (UnknownTokenException e) {
			e.printStackTrace();
			fail("UnknownTokenException: " + e.getMessage());
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("SyntaxException: " + e.getMessage());
		}
		
	}
}
