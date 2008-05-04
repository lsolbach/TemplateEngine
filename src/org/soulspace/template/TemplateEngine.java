/*
 * Created on Jan 2, 2003
 */
package org.soulspace.template;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.datasource.IDataSource;
import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.tokenizer.UnknownTokenException;

/**
 * 
 * @author Ludger Solbach
 *
 */
public interface TemplateEngine {

  /**
   * 
   * @param template
   * @return
   * @throws UnknownTokenException
   * @throws SyntaxException
   */
  public void loadTemplate(String template) throws UnknownTokenException, SyntaxException;

  public void loadTemplate(File templateFile) throws UnknownTokenException, SyntaxException, IOException;

  public void loadTemplates(String[] templates) throws UnknownTokenException, SyntaxException;
  
  public void loadTemplates(File[] templateFiles) throws UnknownTokenException, SyntaxException, IOException;
  
  /**
   * 
   * @param symbolTable
   * @return
   * @throws SyntaxException
   * @throws GenerateException
   */
  String generate(ISymbolTable symbolTable) throws SyntaxException, GenerateException;

  /**
   * 
   * @param dataSource
   * @return
   * @throws SyntaxException
   * @throws GenerateException
   */
  String generate(IDataSource dataSource) throws SyntaxException, GenerateException;

}
