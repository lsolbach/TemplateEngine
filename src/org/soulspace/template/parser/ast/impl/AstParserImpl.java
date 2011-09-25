/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeFactory;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.TokenType;

public class AstParserImpl {

	private AstNodeFactory nodeFactory = new AstNodeFactoryImpl();

	public AstParserImpl() {
		super();
	}

	public AstNode parse(TokenList list) throws SyntaxException {
		AstNode node = nodeFactory.create(AstNodeType.ROOT, null);
		node.addChildNode(parseTerm(list, node, false));
		return node;
	}

	public AstNode parseTerm(TokenList list, AstNode parent, boolean inBlock)
			throws SyntaxException {
		AstNode node = nodeFactory.create(AstNodeType.TERM, list
				.lookUpLastToken(), parent);
		// Loop through token list
		while (list.hasToken()) {
			// Process token
			if (list.checkType(TokenType.TEXT)) {
				node.addChildNode(parseText(list, node));
			} else if (checkForStatement(list)) {
				// Statement or block
				node.addChildNode(parseStatement(list, node));
			} else if (checkForExpression(list)) {
				// Expression
				node.addChildNode(parseExpression(list, node));
			} else if (list.checkType(TokenType.BLOCK_END)) {
				// FIXME check BLOCK_END in parseStatement
				if (!inBlock) {
					throw new SyntaxException("BLOCK_END encountered while not in block! " + list.getToken());
				}
				break;
			} else {
				System.err.println("Unexpected token: " + list.getToken());
				break;
			}
		}
		return node;
	}

	AstNode parseStatement(TokenList list, AstNode parent)
			throws SyntaxException {
		if (list.checkType(TokenType.IF)) {
			return parseIf(list, parent);
		} else if (list.checkType(TokenType.WHILE)) {
			return parseWhile(list, parent);
		} else if (list.checkType(TokenType.FOREACH)) {
			return parseForeach(list, parent);
		} else if (list.checkType(TokenType.DECLARATION)) {
			return parseDeclaration(list, parent);
		} else if (list.checkType(TokenType.BLOCK_BEGIN)) {
			list.skipToken();
			AstNode node = parseTerm(list, parent, true);
			list.validateType(TokenType.BLOCK_END);
			list.skipToken();
			return node;
		} else {
			System.out.println("TODO: " + list.getToken());
		}
		return null;
	}

	/**
	 * @param list
	 * @return
	 */
	private AstNode parseForeach(TokenList list, AstNode parent)
			throws SyntaxException {
		// parse syntax: foreach x | x:Name eq 'Blub' <- xList { } ?
		list.validateType(TokenType.FOREACH);
		AstNode node = nodeFactory.create(list.getToken(), parent);
		list.skipToken();

		IdentifierNodeImpl idNode = (IdentifierNodeImpl) parseExpression(list, node);
		node.addChildNode(idNode);
		if (list.checkType(TokenType.FILTER)) {
			list.skipToken();
			AstNode filterNode = parseExpression(list, node);
			node.addChildNode(filterNode);
		}
		list.validateType(TokenType.FOREACH_ASSIGN);
		list.skipToken();
		AstNode exNode = parseExpression(list, node);
		node.addChildNode(exNode);

		node.addChildNode(parseStatement(list, node));
		return node;
	}

	/**
	 * @param list
	 * @return
	 */
	AstNode parseWhile(TokenList list, AstNode parent)
			throws SyntaxException {
		list.validateType(TokenType.WHILE);
		AstNode node = nodeFactory.create(list.getToken(), parent);
		list.skipToken();

		node.addChildNode(parseExpression(list, node));
		node.addChildNode(parseStatement(list, node));

		return node;
	}

	/**
	 * @param list
	 * @return
	 */
	AstNode parseIf(TokenList list, AstNode parent) throws SyntaxException {
		list.validateType(TokenType.IF);
		AstNode node = nodeFactory.create(list.getToken(), parent);

		// skip if
		list.skipToken();
		// parse condition
		node.addChildNode(parseExpression(list, node));
		// parse if branch
		node.addChildNode(parseStatement(list, node));

		// parse else branch
		if (list.checkType(TokenType.ELSE)) {
			// skip else
			list.skipToken();
			node.addChildNode(parseStatement(list, node));
		}
		return node;
	}

	private AstNode parseDeclaration(TokenList list, AstNode parent)
			throws SyntaxException {
		Token token = list.getToken();
		AstNode node = null;
		AstNode child = null;
		String type = token.getData();

		list.skipToken();
		list.validateType(TokenType.IDENTIFIER);

		if (list.checkNextType(TokenType.PAREN_LEFT)) {
			// method declaration
			MethodNodeImpl mNode = null;
			token = list.getToken();
			String methodName = token.getData();
			mNode = (MethodNodeImpl) nodeFactory.create(AstNodeType.METHOD, token,
					parent);
			mNode.setData(methodName);
			mNode.setReturnType(type);
			list.skipToken();
			parseMethodDeclaration(list, mNode);
			if(!methodName.equals("fn")) {
				mNode.addMethodNode(mNode);
			}
			if(parent.getType().equals(AstNodeType.ASSIGN)) {
				// return method node because it gets assigned
				return mNode;
			} else {
				// don't return a node, because the method declaration is added to
				// the method registry
				// and not to the parent node
				return null;
			}
		} else {
			// symbol declaration
			node = nodeFactory.create(token, parent);
			child = parseExpression(list, node);
			node.addChildNode(child);
		}

		return node;
	}

	private AstNode parseMethodAssignmentDeclaration(TokenList list, AstNode parent) {
		Token token = list.getToken();
		AstNode node = null;
		AstNode child = null;
		String type = token.getData();

		list.skipToken();
		list.validateType(TokenType.IDENTIFIER);

		MethodNodeImpl mNode = null;
		token = list.getToken();
		String methodName = token.getData();
		mNode = (MethodNodeImpl) nodeFactory.create(AstNodeType.METHOD, token,
				parent);
		mNode.setData(methodName);
		mNode.setReturnType(type);
		// don't register node in method registry
		list.skipToken();
		parseMethodDeclaration(list, mNode);
		return mNode;
	}
	
	private AstNode parseMethodDeclaration(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = parent;
		AstNode child = null;

		list.validateType(TokenType.PAREN_LEFT);
		// skip left parenthesis
		list.skipToken();
		child = parseParameterDeclarationList(list, node);
		node.addChildNode(child);
		list.validateType(TokenType.PAREN_RIGHT);
		// skip right parenthesis
		list.skipToken();

		list.validateType(TokenType.BLOCK_BEGIN);
		child = parseStatement(list, node);
		node.addChildNode(child);

		return parent;
	}

	/**
	 * Create a parameter list node and add the parameter declarations to it, if
	 * any.
	 * 
	 * @param list
	 * @param parent
	 * @return
	 * @throws SyntaxException
	 */
	private AstNode parseParameterDeclarationList(TokenList list,
			AstNode parent) throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		node = nodeFactory.create(AstNodeType.PARAM_LIST, list
				.lookUpLastToken(), parent);

		while (list.checkType(TokenType.DECLARATION)) {
			child = parseDeclaration(list, node);
			node.addChildNode(child);
			if (list.checkType(TokenType.SEPERATOR)) {
				// skip seperator
				list.skipToken();
			}

		}
		return node;
	}

	/**
	 * 
	 * @param list
	 * @param node
	 * @return
	 */
	private AstNode parseExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		return parseAssignExpression(list, parent);
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseAssignExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		// left hand side expression (results in identifier)
		child = parseLogicalOrExpression(list, parent);

		if (list.checkType(TokenType.ASSIGN)) {
			// create ASSIGN node
			node = nodeFactory.create(list.getToken(), parent);
			// add left hand side node
			node.addChildNode(child);
			list.skipToken();
			if(list.checkType(TokenType.DECLARATION)) {
				// method assignment
				child = parseMethodAssignmentDeclaration(list, node);
			} else {
				// expression assignment
				child = parseLogicalOrExpression(list, parent);
			}
			// add right hand side
			node.addChildNode(child);
		} else {
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseLogicalOrExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseLogicalAndExpression(list, parent);

		while (list.checkType(TokenType.LOGICAL_OR)) {

			if (node == null) {
				// node doesn't exist yet, create node and add first child
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
			}
			list.skipToken();

			child = parseLogicalAndExpression(list, parent);
			node.addChildNode(child);
		}

		if (node == null) {
			// not a logical OR expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseLogicalAndExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseEqualExpression(list, parent);

		while (list.checkType(TokenType.LOGICAL_AND)) {
			if (node == null) {
				// node doesn't exist yet, create node and add first child
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
			}
			list.skipToken();

			child = parseEqualExpression(list, parent);
			node.addChildNode(child);
		}

		if (node == null) {
			// not a logical and expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseEqualExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseRelationalExpression(list, parent);

		if (checkForEqualOperator(list)) {
			node = nodeFactory.create(list.getToken(), parent);
			node.addChildNode(child);
			list.skipToken();

			child = parseEqualExpression(list, parent);
			node.addChildNode(child);
		} else {
			// not an equal expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseRelationalExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseAdditiveExpression(list, parent);

		if (checkForRelationalOperator(list)) {
			node = nodeFactory.create(list.getToken(), parent);
			node.addChildNode(child);
			list.skipToken();

			child = parseAdditiveExpression(list, parent);
			node.addChildNode(child);
		} else {
			// not a relational expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseAdditiveExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseMultiplicativeExpression(list, parent);

		// token type for the node
		TokenType tokenType = null;

		while (checkForAdditionOperator(list)) {
			if (node == null) {
				// node doesn't exist yet, create node and add first child
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			} else if (tokenType == null || !list.checkType(tokenType)) {
				// current token is not of the same type as the node
				child = node;
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			}
			list.skipToken();

			child = parseMultiplicativeExpression(list, parent);
			node.addChildNode(child);
		}

		if (node == null) {
			// not an additive expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseMultiplicativeExpression(TokenList list,
			AstNode parent) throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		child = parseUnaryExpression(list, parent);
		
		// token type for the node
		TokenType tokenType = null;

		while (checkForMultiplicationOperator(list)) {
			// support for 'a * b * c' or 'a / b / c', ...
			if (node == null) {
				// node doesn't exist yet, create node and add first child
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			} else if (tokenType == null || !list.checkType(tokenType)) {
				// current token is not of the same type as the node
				child = node;
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			}
			list.skipToken();

			child = parseUnaryExpression(list, parent);
			node.addChildNode(child);
		}

		if (node == null) {
			// not a multiplicative expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseUnaryExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = null;
		AstNode child = null;

		if (list.checkType(TokenType.MINUS)
				|| list.checkType(TokenType.LOGICAL_NOT)) {
			node = nodeFactory.create(list.getToken(), parent);
			list.skipToken();

			child = parsePrimaryExpression(list, parent);
			node.addChildNode(child);
		} else {
			// not an unary expression
			node = parsePrimaryExpression(list, parent);
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parsePrimaryExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		Token token = list.getToken();
		AstNode node = null;

		if (list.checkType(TokenType.PAREN_LEFT)) {
			list.skipToken();
			node = parseExpression(list, parent);
			list.validateType(TokenType.PAREN_RIGHT);
			list.skipToken();
		} else if (list.checkType(TokenType.NUMBER_CONST)
				|| list.checkType(TokenType.STRING_CONST)) {
			node = nodeFactory.create(list.getToken(), parent);
			node.setData(token.getData());
			list.skipToken();
		} else {
			node = parseDereferenceExpression(list, parent);
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseDereferenceExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		Token token = list.getToken();
		AstNode child = null;
		AstNode node = null;

		child = parseIdExpression(list, parent);

		// token type for the node
		TokenType tokenType = null;

		// parse dereferenciations
		while (list.checkType(TokenType.DEREFERENCE)) {
			if (node == null) {
				// node doesn't exist yet, create node and add first child
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			} else if (tokenType == null
					|| !list.getToken().getType().equals(tokenType)) {
				// current token is not of the same type as the node
				child = node;
				node = nodeFactory.create(list.getToken(), parent);
				node.addChildNode(child);
				tokenType = list.getToken().getType();
			}
			list.skipToken();

			child = parseIdExpression(list, parent);
			node.addChildNode(child);
			child = node;
		}

		node = null;
		// parse type method calls
		while (list.checkType(TokenType.TYPE_METHOD_CALL)) {
			// FIXME check if node is set to implement cascading type method calls
			token = list.getNextToken();
			if(node != null) {
				child = node;
			}
			node = nodeFactory.create(AstNodeType.TYPE_METHOD_CALL, token,
					parent);
			node.setData(token.getData());
			node.addChildNode(child);
			// skip identifier and left parenthesis
			list.skipToken();
			list.skipToken();

			// parse arg list
			node.addChildNode(parseArgList(list, node));
			list.validateType(TokenType.PAREN_RIGHT);
			list.skipToken();
		}

		if (node == null) {
			// not a deref expression
			node = child;
		}

		return node;
	}

	/**
	 * @param list
	 * @param parent
	 */
	private AstNode parseIdExpression(TokenList list, AstNode parent)
			throws SyntaxException {
		Token token = list.getToken();
		AstNode node = null;

		node = nodeFactory.create(token, parent);
		list.skipToken();
		if (list.checkType(TokenType.BRACKET_LEFT)) {
			// skip left bracket
			list.skipToken();
			node.addChildNode(parseExpression(list, node));
			list.validateType(TokenType.BRACKET_RIGHT);
			list.skipToken();
		} else if (list.checkType(TokenType.PAREN_LEFT)) {
			node = nodeFactory.create(AstNodeType.METHOD_CALL, token, parent);
			node.setData(token.getData());
			list.skipToken();
			node.addChildNode(parseArgList(list, node));
			list.validateType(TokenType.PAREN_RIGHT);
			list.skipToken();
		}

		return node;
	}

	private AstNode parseArgList(TokenList list, AstNode parent)
			throws SyntaxException {
		AstNode node = nodeFactory.create(AstNodeType.ARG_LIST, list
				.lookUpLastToken(), parent);

		while (checkForExpression(list)) {
			node.addChildNode(parseExpression(list, node));
			if (list.checkType(TokenType.SEPERATOR)) {
				// skip seperator
				list.skipToken();
			}
		}
		return node;
	}

	/**
	 * @param list
	 * @return
	 */
	AstNode parseText(TokenList list, AstNode parent) throws SyntaxException {
		list.validateType(TokenType.TEXT);
		AstNode node = nodeFactory.create(list.getToken(), parent);
		// skip to next
		list.skipToken();
		return node;
	}

	boolean checkForStatement(TokenList list) {
		if (list.checkType(TokenType.IF) || list.checkType(TokenType.WHILE)
				|| list.checkType(TokenType.FOREACH)
				|| list.checkType(TokenType.DECLARATION)
				|| list.checkType(TokenType.BLOCK_BEGIN)) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkForExpression(TokenList list) {
		if (list.checkType(TokenType.PAREN_LEFT)
				|| list.checkType(TokenType.LOGICAL_NOT)
				|| list.checkType(TokenType.MINUS)
				|| list.checkType(TokenType.IDENTIFIER)
				|| list.checkType(TokenType.NUMBER_CONST)
				|| list.checkType(TokenType.STRING_CONST)) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkForEqualOperator(TokenList list) {
		if (list.checkType(TokenType.EQUAL)
				|| list.checkType(TokenType.NOT_EQUAL)
				|| list.checkType(TokenType.STRING_EQUAL)
				|| list.checkType(TokenType.STRING_NOT_EQUAL)) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkForRelationalOperator(TokenList list) {
		if (list.checkType(TokenType.GREATER)
				|| list.checkType(TokenType.GREATER_EQUAL)
				|| list.checkType(TokenType.LESS)
				|| list.checkType(TokenType.LESS_EQUAL)) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkForMultiplicationOperator(TokenList list) {
		if (list.checkType(TokenType.MULT)
				|| list.checkType(TokenType.DIV)
				|| list.checkType(TokenType.IDIV)
				|| list.checkType(TokenType.MODULO)) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkForAdditionOperator(TokenList list) {
		if (list.checkType(TokenType.PLUS)
				|| list.checkType(TokenType.MINUS)) {
			return true;
		} else {
			return false;
		}
	}

}
