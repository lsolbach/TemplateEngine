/*
 * Created on Mar 15, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.IAstNodeFactory;
import org.soulspace.template.parser.ast.IAstNodeType;
import org.soulspace.template.tokenizer.IToken;
import org.soulspace.template.tokenizer.TokenType;

/**
 * @author soulman
 *
 */
public class AstNodeFactory implements IAstNodeFactory {

  /**
   * 
   */
  public AstNodeFactory() {
    super();
  }

  /**
   * 
   */
  public IAstNode create(IAstNodeType type, IToken token, IAstNode parent) {
    AbstractAstNode node = null;
    if(type.equals(AstNodeType.TERM)) {
      node = new TermNode();
    } else if(type.equals(AstNodeType.ROOT)) {
      node = new RootNode();
    } else if(type.equals(AstNodeType.ARG_LIST)) {
      node = new ArgListNode();
    } else if(type.equals(AstNodeType.PARAM_LIST)) {
      node = new ParamListNode();
    } else if(type.equals(AstNodeType.METHOD)) {
    	node = new MethodNode();
    } else if(type.equals(AstNodeType.METHOD_CALL)) {
    	node = new MethodCallNode();
    } else if(type.equals(AstNodeType.TYPE_METHOD_CALL)) {
    	node = new TypeMethodCallNode();
    } else {
    	throw new SyntaxException("Unknown node type: " + type.getName());
    }
    node.setParent(parent);
    if(token != null) {
      node.setLine(token.getLine());
      node.setTemplate(token.getTemplate());    	
    }

    return node;
  }
  
  /**
   * 
   */
  public IAstNode create(IToken token, IAstNode parent) {
    AbstractAstNode node = null;

    if(token.getType().equals(TokenType.IDENTIFIER)) {
      node = new IdentifierNode();
      node.setData(token.getData());
    } else if(token.getType().equals(TokenType.TEXT)) {
      node = new TextNode();
      node.setData(token.getData());
    } else if(token.getType().equals(TokenType.XML_DECL)) {
      node = new XmlDeclNode();
      node.setData(token.getData());
    } else if(token.getType().equals(TokenType.FOREACH)) {
      node = new ForeachNode();
    } else if(token.getType().equals(TokenType.IF)) {
      node = new IfNode();
    } else if(token.getType().equals(TokenType.WHILE)) {
      node = new WhileNode();
    } else if(token.getType().equals(TokenType.DECLARATION)) {
      node = new DeclarationNode();
      node.setData(token.getData());
    } else if(token.getType().equals(TokenType.ASSIGN)) {
      node = new AssignNode();
    } else if(token.getType().equals(TokenType.LOGICAL_OR)) {
      node = new LogicalOrNode();
    } else if(token.getType().equals(TokenType.LOGICAL_AND)) {
      node = new LogicalAndNode();
    } else if(token.getType().equals(TokenType.LOGICAL_NOT)) {
      node = new LogicalNotNode();
    } else if(token.getType().equals(TokenType.GREATER)) {
      node = new GreaterNode();
    } else if(token.getType().equals(TokenType.GREATER_EQUAL)) {
      node = new GreaterEqualNode();
    } else if(token.getType().equals(TokenType.LESS)) {
      node = new LessNode();
    } else if(token.getType().equals(TokenType.LESS_EQUAL)) {
      node = new LessEqualNode();
    } else if(token.getType().equals(TokenType.EQUAL)) {
      node = new EqualNode();
    } else if(token.getType().equals(TokenType.NOT_EQUAL)) {
      node = new NotEqualNode();
    } else if(token.getType().equals(TokenType.STRING_EQUAL)) {
      node = new StringEqualNode();
    } else if(token.getType().equals(TokenType.STRING_NOT_EQUAL)) {
      node = new StringNotEqualNode();
    } else if(token.getType().equals(TokenType.PLUS)) {
      node = new PlusNode();
    } else if(token.getType().equals(TokenType.MINUS)) {
      node = new MinusNode();
    } else if(token.getType().equals(TokenType.MULT)) {
      node = new MultNode();
    } else if(token.getType().equals(TokenType.DIV)) {
      node = new DivNode();
    } else if(token.getType().equals(TokenType.IDIV)) {
      node = new IntegerDivNode();
    } else if(token.getType().equals(TokenType.MODULO)) {
      node = new ModuloNode();
    } else if(token.getType().equals(TokenType.DEREFERENCE)) {
      node = new DereferenceNode();
    } else if(token.getType().equals(TokenType.NUMBER_CONST)) {
      node = new NumericConstNode();
      node.setData(token.getData());
    } else if(token.getType().equals(TokenType.STRING_CONST)) {
      node = new StringConstNode();
      node.setData(token.getData());
    } else {
    	throw new SyntaxException("Unknown token type: " + token.getType());
    }
    node.setLine(token.getLine());
    node.setTemplate(token.getTemplate());
    
    if(parent != null) {
    	node.setParent(parent);
    }
    return node;
  }
  
  /**
   * 
   */
  public IAstNode create(IToken token) {
    return create(token, null);
  }

  /**
   * 
   * @param type
   * @return
   */
  public IAstNode create(IAstNodeType type, IToken token) {
  	return create(type, token, null);
  }
  
}