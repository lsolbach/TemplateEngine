/*
 * Created on Apr 18, 2005
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.Generator;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.tokenizer.TokenList;

public class GeneratorImpl implements Generator {

  public GeneratorImpl() {
    super();
  }

  public String generate(TokenList tokenList, ISymbolTable symbolTable)
      throws GenerateException {
    IAstNode root = null;
    AstParserImpl astParser = new AstParserImpl();
    AstGeneratorImpl astGenerator = new AstGeneratorImpl();
    
    try {
      root = astParser.parseTerm(tokenList, null, false);
    } catch (SyntaxException e) {
      e.printStackTrace();
    }
    String result = astGenerator.generate(root, symbolTable);
    
    return result;

  }

  public IAstNode parseAst(TokenList tokenList) throws SyntaxException {    
    AstParserImpl astParser = new AstParserImpl();
    
    IAstNode root = null;
    if (tokenList == null || tokenList.size() == 0) {
      SyntaxException ex = new SyntaxException(
          "Empty token list, you have to parse a template before trying to generate HTML!");
      throw ex;
    }
    root = astParser.parseTerm(tokenList, null, false);
    
    return root;
  }
  
}
