/*
 * Created on Mar 15, 2005
 */
package org.soulspace.template.parser.ast;

import org.soulspace.template.tokenizer.IToken;

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
  public IAstNode create(IToken token, IAstNode parent);

  /**
   * 
   * @param type
   * @return
   */
  public IAstNode create(IAstNodeType type, IToken token, IAstNode parent);

  /**
   * 
   * @param token
   * @return
   */
  public IAstNode create(IToken token);
  
  /**
   * 
   * @param type
   * @return
   */
  public IAstNode create(IAstNodeType type, IToken token);
  
}
