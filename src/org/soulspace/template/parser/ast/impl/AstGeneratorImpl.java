/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbolTable;

public class AstGeneratorImpl {

  public AstGeneratorImpl() {
    super();
  }

  public String generate(IAstNode root, ISymbolTable symbolTable) throws GenerateException { 
    root.setSymbolTable(symbolTable);
    String result = root.generateSymbol().evaluate();
    return result;
  }
    
}
