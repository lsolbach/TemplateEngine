/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeFactory;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenType;

/**
 * @author soulman
 * 
 */
public class AstNodeFactoryImpl implements AstNodeFactory {

	/**
	 * 
	 */
	public AstNodeFactoryImpl() {
		super();
	}

	/**
	 * 
	 */
	public AstNode create(AstNodeType type, Token token, AstNode parent) {
		AbstractAstNode node = null;
		if (type.equals(AstNodeType.TERM)) {
			node = new TermNodeImpl();
		} else if (type.equals(AstNodeType.ROOT)) {
			node = new RootNodeImpl();
		} else if (type.equals(AstNodeType.ARG_LIST)) {
			node = new ArgListNodeImpl();
		} else if (type.equals(AstNodeType.PARAM_LIST)) {
			node = new ParamListNodeImpl();
		} else if (type.equals(AstNodeType.METHOD)) {
			node = new MethodNodeImpl();
		} else if (type.equals(AstNodeType.METHOD_CALL)) {
			node = new MethodCallNodeImpl();
		} else if (type.equals(AstNodeType.TYPE_METHOD_CALL)) {
			node = new TypeMethodCallNodeImpl();
		} else {
			throw new SyntaxException("Unknown node type: " + type.getName()
					+ "! Template " + token.getTemplate()
					+ ", Line " + token.getLine());
		}
		node.setParent(parent);
		if (token != null) {
			node.setLine(token.getLine());
			node.setTemplate(token.getTemplate());
		}

		return node;
	}

	/**
	 * 
	 */
	public AstNode create(Token token, AstNode parent) {
		AbstractAstNode node = null;

		if (token.getType().equals(TokenType.IDENTIFIER)) {
			node = new IdentifierNodeImpl();
			node.setData(token.getData());
		} else if (token.getType().equals(TokenType.TEXT)) {
			node = new TextNodeImpl();
			node.setData(token.getData());
		} else if (token.getType().equals(TokenType.XML_DECL)) {
			node = new XmlDeclNodeImpl();
			node.setData(token.getData());
		} else if (token.getType().equals(TokenType.FOREACH)) {
			node = new ForeachNodeImpl();
		} else if (token.getType().equals(TokenType.IF)) {
			node = new IfNodeImpl();
		} else if (token.getType().equals(TokenType.WHILE)) {
			node = new WhileNodeImpl();
		} else if (token.getType().equals(TokenType.DECLARATION)) {
			node = new DeclarationNodeImpl();
			node.setData(token.getData());
		} else if (token.getType().equals(TokenType.ASSIGN)) {
			node = new AssignNodeImpl();
		} else if (token.getType().equals(TokenType.LOGICAL_OR)) {
			node = new LogicalOrNodeImpl();
		} else if (token.getType().equals(TokenType.LOGICAL_AND)) {
			node = new LogicalAndNodeImpl();
		} else if (token.getType().equals(TokenType.LOGICAL_NOT)) {
			node = new LogicalNotNodeImpl();
		} else if (token.getType().equals(TokenType.GREATER)) {
			node = new GreaterNodeImpl();
		} else if (token.getType().equals(TokenType.GREATER_EQUAL)) {
			node = new GreaterEqualNodeImpl();
		} else if (token.getType().equals(TokenType.LESS)) {
			node = new LessNodeImpl();
		} else if (token.getType().equals(TokenType.LESS_EQUAL)) {
			node = new LessEqualNodeImpl();
		} else if (token.getType().equals(TokenType.EQUAL)) {
			node = new EqualNodeImpl();
		} else if (token.getType().equals(TokenType.NOT_EQUAL)) {
			node = new NotEqualNodeImpl();
		} else if (token.getType().equals(TokenType.STRING_EQUAL)) {
			node = new StringEqualNodeImpl();
		} else if (token.getType().equals(TokenType.STRING_NOT_EQUAL)) {
			node = new StringNotEqualNodeImpl();
		} else if (token.getType().equals(TokenType.PLUS)) {
			node = new PlusNodeImpl();
		} else if (token.getType().equals(TokenType.MINUS)) {
			node = new MinusNodeImpl();
		} else if (token.getType().equals(TokenType.MULT)) {
			node = new MultNodeImpl();
		} else if (token.getType().equals(TokenType.DIV)) {
			node = new DivNodeImpl();
		} else if (token.getType().equals(TokenType.IDIV)) {
			node = new IntegerDivNodeImpl();
		} else if (token.getType().equals(TokenType.MODULO)) {
			node = new ModuloNodeImpl();
		} else if (token.getType().equals(TokenType.DEREFERENCE)) {
			node = new DereferenceNodeImpl();
		} else if (token.getType().equals(TokenType.NUMBER_CONST)) {
			node = new NumericConstNodeImpl();
			node.setData(token.getData());
		} else if (token.getType().equals(TokenType.STRING_CONST)) {
			node = new StringConstNodeImpl();
			node.setData(token.getData());
		} else {
			throw new SyntaxException("Unknown token type: " + token.getType()
					+ "! Template " + token.getTemplate()
					+ ", Line " + token.getLine());
		}
		node.setLine(token.getLine());
		node.setTemplate(token.getTemplate());

		if (parent != null) {
			node.setParent(parent);
		}
		return node;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public AstNode create(AstNodeType type, Token token) {
		return create(type, token, null);
	}

}