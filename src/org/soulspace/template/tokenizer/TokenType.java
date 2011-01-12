/**
 * File: TokenType.java 
 *
 * Created on Dec 3, 2002
 */
package org.soulspace.template.tokenizer;

/**
 * @author soulman
 * 
 *         Typesafe enumeration of the token types
 */
public class TokenType {
	private final String name;

	private TokenType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public final static TokenType IF = new TokenType("IF");
	public final static TokenType ELSE = new TokenType("ELSE");
	public final static TokenType FOREACH = new TokenType("FOREACH");
	public final static TokenType WHILE = new TokenType("WHILE");
	public final static TokenType BREAK = new TokenType("BREAK");
	public final static TokenType CONTINUE = new TokenType("CONTINUE");
	public final static TokenType DECLARATION = new TokenType("DECLARATION");
	public final static TokenType BLOCK_BEGIN = new TokenType("BLOCK_BEGIN");
	public final static TokenType BLOCK_END = new TokenType("BLOCK_END");
	public final static TokenType IDENTIFIER = new TokenType("IDENTIFIER");
	public final static TokenType NUMBER_CONST = new TokenType("NUMBER_CONST");
	public final static TokenType STRING_CONST = new TokenType("STRING_CONST");
	public static final TokenType PAREN_LEFT = new TokenType("PAREN_LEFT");
	public static final TokenType PAREN_RIGHT = new TokenType("PAREN_RIGHT");
	public final static TokenType BRACKET_LEFT = new TokenType("BRACKET_LEFT");
	public final static TokenType BRACKET_RIGHT = new TokenType("BRACKET_RIGHT");
	public final static TokenType FOREACH_ASSIGN = new TokenType(
			"FOREACH_ASSIGN");
	public final static TokenType LESS = new TokenType("LESS");
	public final static TokenType GREATER = new TokenType("GREATER");
	public final static TokenType LESS_EQUAL = new TokenType("LESS_EQUAL");
	public final static TokenType GREATER_EQUAL = new TokenType("GREATER_EQUAL");
	public final static TokenType EQUAL = new TokenType("EQUAL");
	public final static TokenType NOT_EQUAL = new TokenType("NOT_EQUAL");
	public final static TokenType STRING_EQUAL = new TokenType("STRING_EQUAL");
	public final static TokenType STRING_NOT_EQUAL = new TokenType(
			"STRING_NOT_EQUAL");
	public final static TokenType PLUS = new TokenType("PLUS");
	public final static TokenType MINUS = new TokenType("MINUS");
	public final static TokenType MULT = new TokenType("MULT");
	public final static TokenType DIV = new TokenType("DIV");
	public final static TokenType IDIV = new TokenType("IDIV");
	public final static TokenType MODULO = new TokenType("MODULO");
	public final static TokenType LOGICAL_AND = new TokenType("LOGICAL_AND");
	public final static TokenType LOGICAL_OR = new TokenType("LOGICAL_OR");
	public final static TokenType LOGICAL_NOT = new TokenType("LOGICAL_NOT");
	public final static TokenType FILTER = new TokenType("FILTER");
	public final static TokenType DEFINITION = new TokenType("DEFINITION");
	public final static TokenType DEREFERENCE = new TokenType("DEREFERENCE");
	public final static TokenType TYPE_METHOD_CALL = new TokenType(
			"TYPE_METHOD_CALL");
	public final static TokenType ASSIGN = new TokenType("ASSIGN");
	public final static TokenType SEPERATOR = new TokenType("SEPERATOR");
	public final static TokenType EPSILON = new TokenType("EPSILON");
	public final static TokenType XML_DECL = new TokenType("XML_DECL");
	public final static TokenType TEXT = new TokenType("TEXT");

}
