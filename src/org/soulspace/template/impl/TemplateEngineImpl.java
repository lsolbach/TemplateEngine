/**
 * File: TemplateEngine.java 
 *
 * Created on Jan 2, 2003
 */
package org.soulspace.template.impl;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.environment.Environment;
import org.soulspace.template.environment.impl.EnvironmentImpl;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.method.impl.DynamicMethodRegistryImpl;
import org.soulspace.template.method.impl.MethodRegistryImpl;
import org.soulspace.template.method.impl.StaticMethodRegistryImpl;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.util.FileUtils;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.impl.SymbolTableImpl;

/**
 * Implementation of the TemplateEngine interface.
 * 
 * @author Ludger Solbach
 */
public class TemplateEngineImpl implements TemplateEngine {

	private AstNode root;
	private Tokenizer tokenizer = new TokenizerImpl();
	private AstParserImpl parser = new AstParserImpl();

	/**
	 * Constructor
	 */
	public TemplateEngineImpl() {
		MethodRegistryImpl.setMethodRegisty(new StaticMethodRegistryImpl());
	}

	/**
	 * Constructor with an array of packages to scan for type method implementations.
	 * 
	 * @param methodPackages array of package names
	 */
	public TemplateEngineImpl(String[] methodPackages) {
		try {
			DynamicMethodRegistryImpl registry = new DynamicMethodRegistryImpl();
			if (methodPackages != null) {
				for (String packageName : methodPackages) {
					if (packageName != null) {
						registry.registerPackage(packageName);
					}
				}
			}
			MethodRegistryImpl.setMethodRegisty(registry);
		} catch (Exception e) {
			System.out.println("Error initializing dynamic method registry, using static registry");
			MethodRegistryImpl.setMethodRegisty(new StaticMethodRegistryImpl());
		}
	}

	/**
	 * Load and parse the given template
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 */
	public void loadTemplate(String template) throws UnknownTokenException, SyntaxException {
		TokenList tokenList = tokenizer.createTokenList();
		tokenList = tokenize(tokenList, "Template", template);
		parse(tokenList);
	}

	/**
	 * Load and parse the given template file
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 * @throws IOException
	 */
	public void loadTemplate(File templateFile) throws UnknownTokenException, SyntaxException, IOException {
		TokenList tokenList = tokenizer.createTokenList();
		tokenList = tokenize(tokenList, templateFile.getPath(), FileUtils.loadStringFromFile(templateFile));
		parse(tokenList);
	}

	/**
	 * Load and parse the given templates
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 */
	public void loadTemplates(String[] templates) throws UnknownTokenException, SyntaxException {
		TokenList tokenList = tokenizer.createTokenList();
		for (String template : templates) {
			tokenList = tokenize(tokenList, "Template", template);
		}
		parse(tokenList);
	}

	/**
	 * Load and parse the given template files
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 * @throws IOException
	 */
	public void loadTemplates(File[] templateFiles) throws UnknownTokenException, SyntaxException, IOException {
		TokenList tokenList = tokenizer.createTokenList();
		for (File templateFile : templateFiles) {
			tokenList.setCurrentLine(1);
			tokenList = tokenize(tokenList, templateFile.getPath(), FileUtils.loadStringFromFile(templateFile));
		}
		parse(tokenList);
	}

	/**
	 * Generates the output without any external data.
	 * 
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate() throws SyntaxException, GenerateException {
		Environment environment = new EnvironmentImpl(new SymbolTableImpl());
		return root.generateValue(environment).evaluate();
	}

	/**
	 * Generate output for the given symbol table with the parsed template
	 * 
	 * @param symbolTable
	 *            to use for the generation
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate(SymbolTable symbolTable) throws SyntaxException, GenerateException {
		Environment environment = new EnvironmentImpl(symbolTable);
		return root.generateValue(environment).evaluate();
	}

	/**
	 * Generate output for the given data source with the parsed template
	 * 
	 * @param dataSource
	 *            to use for the generation
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate(DataSource dataSource) throws SyntaxException, GenerateException {
		Environment environment = new EnvironmentImpl(dataSource.getSymbolTable());
		return root.generateValue(environment).evaluate();
	}

	private TokenList tokenize(TokenList tokenList, String name, String content) {
		tokenList.setTemplate(name);
		return tokenizer.tokenize(tokenList, content);
	}

	private boolean parse(TokenList tokenList) {
		if (tokenList.size() == 0) {
			return false;
		}
		root = parser.parse(tokenList);
		return true;
	}

}