/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
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
