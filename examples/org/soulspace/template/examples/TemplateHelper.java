/*
 * Created on Aug 6, 2005
 */
package org.soulspace.template.examples;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.datasource.impl.BeanDataSourceImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.impl.TemplateEngineImpl;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.impl.SymbolTableImpl;

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
    result = te.generate(new SymbolTableImpl());

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
  public static String generate(String template, SymbolTable st) {
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
  public static String generate(String template, DataSource dataSource) {
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
    BeanDataSourceImpl ds = new BeanDataSourceImpl(obj);

    te.loadTemplate(templateFile);
    result = te.generate(ds);

    return result;
  }

}
