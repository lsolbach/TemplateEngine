/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.value.SymbolTable;

public class AstGeneratorImpl {

  public AstGeneratorImpl() {
    super();
  }

  public String generate(AstNode root, SymbolTable symbolTable) throws GenerateException { 
    root.setSymbolTable(symbolTable);
    String result = root.generateValue().evaluate();
    return result;
  }
    
}
