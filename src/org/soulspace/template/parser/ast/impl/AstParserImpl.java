/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IAstNodeFactory;
import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.TokenType;

public class AstParserImpl {

  private IAstNodeFactory nodeFactory = new AstNodeFactory();
  
  public AstParserImpl() {
    super();
  }

  public IAstNode parse(TokenList list) throws SyntaxException {
    IAstNode node = nodeFactory.create(AstNodeType.ROOT, null);
    node.addChildNode(parseTerm(list, node, false));
    return node;
  }
  
  public IAstNode parseTerm(TokenList list, IAstNode parent, boolean inBlock) throws SyntaxException {
    IAstNode node = nodeFactory.create(AstNodeType.TERM, parent);    
    // Loop through token list
    while (list.hasToken()) {
      // Process token
      if(list.checkType(TokenType.TEXT)) {
        node.addChildNode(parseText(list, node));
      } else if(list.checkType(TokenType.IF)
          || list.checkType(TokenType.WHILE)
          || list.checkType(TokenType.FOREACH)
          || list.checkType(TokenType.DECLARATION)
          || list.checkType(TokenType.BLOCK_BEGIN)) {
        // Statement or block
        node.addChildNode(parseStatement(list, node));
      } else if(list.checkType(TokenType.PAREN_LEFT)
          || list.checkType(TokenType.LOGICAL_NOT)
          || list.checkType(TokenType.MINUS)
          || list.checkType(TokenType.IDENTIFIER)
          || list.checkType(TokenType.NUMBER_CONST)
          || list.checkType(TokenType.STRING_CONST)) {
        // Expression
        node.addChildNode(parseExpression(list, node));
      } else if(list.checkType(TokenType.BLOCK_END)) {
        if(!inBlock) {
          throw new SyntaxException("BLOCK_END encountered while not in block!");
        }
        list.skipToken();
        break;
      } else {
        System.err.println("Unexpected token: " + list.getToken());
        break;
      }
    }    
    return node;
  }

  IAstNode parseStatement(TokenList list, IAstNode parent) throws SyntaxException {
    if(list.checkType(TokenType.IF)) {
      return parseIf(list, parent);
    } else if(list.checkType(TokenType.WHILE)) {
      return parseWhile(list, parent);
    } else if(list.checkType(TokenType.FOREACH)) {
      return parseForeach(list, parent);
    } else if(list.checkType(TokenType.DECLARATION)) {
      return parseDeclaration(list, parent);
    } else if(list.checkType(TokenType.BLOCK_BEGIN)) {
      list.skipToken();
      return parseTerm(list, parent, true);
    } else  {
      System.out.println("TODO: " + list.getToken());
    }
    return null;
  }
  
  /**
   * @param list
   * @return
   */
  private IAstNode parseForeach(TokenList list, IAstNode parent) throws SyntaxException {
    // parse syntax: foreach x <- xList { }
    list.validateType(TokenType.FOREACH);
    IAstNode node = nodeFactory.create(list.getToken(), parent);
    list.skipToken();
    
  	IdentifierNode idNode = (IdentifierNode) parseExpression(list, node);
    node.addChildNode(idNode);
    list.validateType(TokenType.FOREACH_ASSIGN);
  	list.skipToken();
  	IAstNode exNode = parseExpression(list, node);
    node.addChildNode(exNode);

    node.addChildNode(parseStatement(list, node));
    return node;
  }

  /**
   * @param list
   * @return
   */
  IAstNode parseWhile(TokenList list, IAstNode parent) throws SyntaxException {
    list.validateType(TokenType.WHILE);
    IAstNode node = nodeFactory.create(list.getToken(), parent);
    list.skipToken();
    
    node.addChildNode(parseExpression(list, node));
    node.addChildNode(parseStatement(list, node));
    
    return node;
  }

  /**
   * @param list
   * @return
   */
  IAstNode parseIf(TokenList list, IAstNode parent) throws SyntaxException {
    list.validateType(TokenType.IF);
    IAstNode node = nodeFactory.create(list.getToken(), parent);

    // skip if
    list.skipToken();
    // parse condition
    node.addChildNode(parseExpression(list, node));
    // parse if branch
    node.addChildNode(parseStatement(list, node));

    // parse else branch
    if(list.checkType(TokenType.ELSE)) {
    	// skip else
    	list.skipToken();
      node.addChildNode(parseStatement(list, node));
    }
    return node;
  }

  private IAstNode parseDeclaration(TokenList list, IAstNode parent) throws SyntaxException {
    Token token = list.getToken();
    IAstNode node = null;
    IAstNode child = null;

    list.skipToken();
    list.validateType(TokenType.IDENTIFIER);
    
    if(list.checkNextType(TokenType.PAREN_LEFT)) {
    	// method declaration
    	token = list.getToken();
      String methodName = token.getData();
      node = nodeFactory.create(AstNodeType.METHOD, parent);
      node.setData(methodName);
      node.addMethodNode(node);
      list.skipToken();
      parseMethodDeclaration(list, node);
      // don't return a node, because the method declaration is added to the method registry
      // and not to the parent node
  		return null;
  	} else {
  		// symbol declaration
      node = nodeFactory.create(token, parent);
  		child = parseExpression(list, node);
  		node.addChildNode(child);
  	}

    return node;
  }

  private IAstNode parseMethodDeclaration(TokenList list, IAstNode parent) throws SyntaxException {
    IAstNode node = parent;
    IAstNode child = null;

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
   * Create a parameter list node and add the parameter declarations to it, if any.
   * @param list
   * @param parent
   * @return
   * @throws SyntaxException
   */
  private IAstNode parseParameterDeclarationList(TokenList list, IAstNode parent) throws SyntaxException {
    IAstNode node = null;
    IAstNode child = null;

    node = nodeFactory.create(AstNodeType.PARAM_LIST, parent);

    while(list.checkType(TokenType.DECLARATION)) {
			child = parseDeclaration(list, node);
			node.addChildNode(child);
      if(list.checkType(TokenType.SEPERATOR)) {
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
  private IAstNode parseExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    return parseAssignExpression(list, parent);    
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseAssignExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseLogicalOrExpression(list, parent);
    
    if(list.checkType(TokenType.ASSIGN)) {
      node = nodeFactory.create(list.getToken(), parent);
      node.addChildNode(child);
      list.skipToken();
      
      child = parseLogicalOrExpression(list, parent);
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
  private IAstNode parseLogicalOrExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseLogicalAndExpression(list, parent);

    while(list.checkType(TokenType.LOGICAL_OR)) {

      if(node == null) {
        // node doesn't exist yet, create node and add first child
        node = nodeFactory.create(list.getToken(), parent);
        node.addChildNode(child);
      }
      list.skipToken();
      
      child = parseLogicalAndExpression(list, parent);
      node.addChildNode(child);
    }
    
    if(node == null) {
      // not a logical OR expression
      node = child;
    }

    return node;
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseLogicalAndExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseEqualExpression(list, parent);
    
    while(list.checkType(TokenType.LOGICAL_AND)) {
      if(node == null) {
        // node doesn't exist yet, create node and add first child
        node = nodeFactory.create(list.getToken(), parent);
        node.addChildNode(child);        
      }
      list.skipToken();
      
      child = parseEqualExpression(list, parent);
      node.addChildNode(child);
    }
    
    if(node == null) {
      // not a logical and expression
      node = child;
    }

    return node;
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseEqualExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseRelationalExpression(list, parent);

    if(list.checkType(TokenType.EQUAL)
    		|| list.checkType(TokenType.NOT_EQUAL)
    		|| list.checkType(TokenType.STRING_EQUAL)
    		|| list.checkType(TokenType.STRING_NOT_EQUAL)
    		) {
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
  private IAstNode parseRelationalExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseAdditiveExpression(list, parent);
    
    if(list.checkType(TokenType.GREATER)
    		|| list.checkType(TokenType.GREATER_EQUAL)
    		|| list.checkType(TokenType.LESS)
    		|| list.checkType(TokenType.LESS_EQUAL)) {
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
  private IAstNode parseAdditiveExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseMultiplicativeExpression(list, parent);

    // token type for the node
    TokenType tokenType = null;

    while(list.checkType(TokenType.PLUS)
    		|| list.checkType(TokenType.MINUS)) {
      if(node == null) {
        // node doesn't exist yet, create node and add first child
        node = nodeFactory.create(list.getToken(), parent);
        node.addChildNode(child);
        tokenType = list.getToken().getType();
      } else if(tokenType == null
          || !list.checkType(tokenType)) {
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

    if(node == null) {
      // not an additive expression
      node = child;
    }

    return node;
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseMultiplicativeExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;
    
    child = parseUnaryExpression(list, parent);
    
    // token type for the node
    TokenType tokenType = null;

    while(list.checkType(TokenType.MULT)
    		|| list.checkType(TokenType.DIV)
    		|| list.checkType(TokenType.IDIV)
    		|| list.checkType(TokenType.MODULO)) {
      // support for 'a * b * c' or 'a / b / c', ...
      if(node == null) {
        // node doesn't exist yet, create node and add first child
        node = nodeFactory.create(list.getToken(), parent);
        node.addChildNode(child);
        tokenType = list.getToken().getType();
      } else if(tokenType == null
          || !list.checkType(tokenType)) {
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

    if(node == null) {
      // not a multiplicative expression
      node = child;
    }
    
    return node;
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseUnaryExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    IAstNode node = null;
    IAstNode child = null;

    if(list.checkType(TokenType.MINUS)
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
  private IAstNode parsePrimaryExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    Token token = list.getToken();
    IAstNode node = null;
    
    if(list.checkType(TokenType.PAREN_LEFT)) {
      list.skipToken();
      node = parseExpression(list, parent);
      list.validateType(TokenType.PAREN_RIGHT);
      list.skipToken();
    } else if(list.checkType(TokenType.NUMBER_CONST)
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
  private IAstNode parseDereferenceExpression(TokenList list, IAstNode parent) throws SyntaxException  {
    Token token = list.getToken();
    IAstNode child = null;
    IAstNode node = null;
    
    child = parseIdExpression(list, parent);

    // token type for the node
    TokenType tokenType = null;

    // parse dereferenciations
    while(list.checkType(TokenType.DEREFERENCE)) {
      if(node == null) {
        // node doesn't exist yet, create node and add first child
        node = nodeFactory.create(list.getToken(), parent);
        node.addChildNode(child);
        tokenType = list.getToken().getType();
      } else if(tokenType == null
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
    
    // parse type method calls
    while(list.checkType(TokenType.TYPE_METHOD_CALL)) {
    	token = list.getNextToken();
      node = nodeFactory.create(AstNodeType.TYPE_METHOD_CALL, parent);
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
    
    if(node == null) {
      // not a deref expression
      node = child;
    }    
    
    return node;
  }

  /**
   * @param list
   * @param parent
   */
  private IAstNode parseIdExpression(TokenList list, IAstNode parent) throws SyntaxException {
    Token token = list.getToken();
    IAstNode node = null;
    
    node = nodeFactory.create(token, parent);
    list.skipToken();
    if(list.checkType(TokenType.BRACKET_LEFT)) {
      // skip left bracket
      list.skipToken();
      node.addChildNode(parseExpression(list, node));
      list.validateType(TokenType.BRACKET_RIGHT);
      list.skipToken();
    } else if(list.checkType(TokenType.PAREN_LEFT)) {
    	node = nodeFactory.create(AstNodeType.METHOD_CALL, parent);
    	node.setData(token.getData());
      list.skipToken();
      node.addChildNode(parseArgList(list, node));
      list.validateType(TokenType.PAREN_RIGHT);
      list.skipToken();
    }
    
    return node;
  }

  private IAstNode parseArgList(TokenList list, IAstNode parent) throws SyntaxException {
    IAstNode node = nodeFactory.create(AstNodeType.ARG_LIST, parent);
    
    while(list.checkType(TokenType.PAREN_LEFT)
            || list.checkType(TokenType.LOGICAL_NOT)
            || list.checkType(TokenType.MINUS)
            || list.checkType(TokenType.IDENTIFIER)
            || list.checkType(TokenType.NUMBER_CONST)
            || list.checkType(TokenType.STRING_CONST)
            ) {
    	node.addChildNode(parseExpression(list, node));
      if(list.checkType(TokenType.SEPERATOR)) {
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
  IAstNode parseText(TokenList list, IAstNode parent) throws SyntaxException {
    list.validateType(TokenType.TEXT);
    IAstNode node = nodeFactory.create(list.getToken(), parent);
    // skip to next
    list.skipToken();
    return node;
  }
  
}
