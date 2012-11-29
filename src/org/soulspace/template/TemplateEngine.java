/*
 * Created on Jan 2, 2003
 */
package org.soulspace.template;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.value.SymbolTable;

/**
 * 
 * @author Ludger Solbach
 *
 */
public interface TemplateEngine {

  /**
   * Initializes the template engine using the given string as template.
   * @param template
   * @return
   * @throws UnknownTokenException
   * @throws SyntaxException
   */
  void loadTemplate(String template) throws UnknownTokenException, SyntaxException;

  /**
   * Initializes the template engine loading the given file as template.
   * @param templateFile
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws IOException
   */
  void loadTemplate(File templateFile) throws UnknownTokenException, SyntaxException, IOException;
  
  /**
   * Initializes the template engine using the given string template array.
   * @param templates
   * @throws UnknownTokenException
   * @throws SyntaxException
   */
  void loadTemplates(String[] templates) throws UnknownTokenException, SyntaxException;
  
  /**
   * Initializes the template engine loading the files from the given file array.
   * @param templateFiles
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws IOException
   */
  void loadTemplates(File[] templateFiles) throws UnknownTokenException, SyntaxException, IOException;
  
  /**
   * Generates the output with data from the given symbol table.
   * @param symbolTable
   * @return
   * @throws SyntaxException
   * @throws GenerateException
   */
  String generate(SymbolTable symbolTable) throws SyntaxException, GenerateException;

  /**
   * Generates the output with data from the given data source.
   * @param dataSource
   * @return output
   * @throws SyntaxException
   * @throws GenerateException
   */
  String generate(DataSource dataSource) throws SyntaxException, GenerateException;

}
