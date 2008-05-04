/*
 * Created on Aug 17, 2005
 */
package org.soulspace.template.parser;

import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.tokenizer.TokenList;

public interface Generator {

  /**
   * 
   * @param tokenList
   * @param symbolTable
   * @return
   * @throws GenerateException
   */
  String generate(TokenList tokenList, ISymbolTable symbolTable) throws GenerateException;
}
