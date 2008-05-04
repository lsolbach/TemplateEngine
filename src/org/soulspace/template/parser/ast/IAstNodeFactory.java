/*
 * Created on Mar 15, 2005
 */
package org.soulspace.template.parser.ast;

import org.soulspace.template.tokenizer.Token;

/**
 * @author soulman
 *
 */
public interface IAstNodeFactory {
  
  /**
   * 
   * @param token
   * @param parent
   * @return
   */
  public IAstNode create(Token token, IAstNode parent);

  /**
   * 
   * @param type
   * @return
   */
  public IAstNode create(IAstNodeType type, IAstNode parent);

  /**
   * 
   * @param token
   * @return
   */
  public IAstNode create(Token token);
  
  /**
   * 
   * @param type
   * @return
   */
  public IAstNode create(IAstNodeType type);
  
}
