/*
 * Created on Aug 6, 2005
 */
package org.soulspace.template.examples;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.IDataSource;
import org.soulspace.template.datasource.impl.BeanDataSource;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.SymbolTable;
import org.soulspace.template.tokenizer.UnknownTokenException;

public class TemplateHelper {

  public TemplateHelper() {
    super();
  }

  /**
   * Generates the template without data.
   * @param template template as string
   * @return generatet output
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws GenerateException
   */
  public static String generate(String template) {
  	TemplateEngine te = new TemplateEngineImpl();
    String result;

    te.loadTemplate(template);
    result = te.generate(new SymbolTable());

    return result;
  }

  /**
   * Generates the template with the data in the symbol table.
   * @param template template as string
   * @param st data as symbol table
   * @return generated output
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws GenerateException
   */
  public static String generate(String template, ISymbolTable st) {
    TemplateEngine te = new TemplateEngineImpl();
    String result;

    te.loadTemplate(template);
    result = te.generate(st);

    return result;
  }

  /**
   * Generates the template with the data in the data source.
   * @param template template as string
   * @param dataSource data as symbol table
   * @return generated output
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws GenerateException
   */
  public static String generate(String template, IDataSource dataSource) {
    TemplateEngine te = new TemplateEngineImpl();
    String result;

    te.loadTemplate(template);
    result = te.generate(dataSource);

    return result;
  }

  /**
   * Reads the template file and generates the output with data from the specified object. 
   * @param templateFile template as file
   * @param obj data object
   * @return generated output
   * @throws UnknownTokenException
   * @throws SyntaxException
   * @throws GenerateException
   * @throws IOException
   */
  public static String generate(File templateFile, Object obj)
      throws UnknownTokenException, SyntaxException, GenerateException,
      IOException {
    String result;
    TemplateEngine te = new TemplateEngineImpl();
    BeanDataSource ds = new BeanDataSource(obj);

    te.loadTemplate(templateFile);
    result = te.generate(ds);

    return result;
  }

}
