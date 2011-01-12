/*
 * Created on Mar 15, 2005
 */
package org.soulspace.template.parser.ast;

import org.soulspace.template.tokenizer.Token;

/**
 * @author soulman
 *
 */
public interface AstNodeFactory {
  
  /**
   * 
   * @param token
   * @param parent
   * @return
   */
  public AstNode create(Token token, AstNode parent);

  /**
   * 
   * @param type
   * @return
   */
  public AstNode create(AstNodeType type, Token token, AstNode parent);

  /**
   * 
   * @param type
   * @return
   */
  public AstNode create(AstNodeType type, Token token);
  
}
