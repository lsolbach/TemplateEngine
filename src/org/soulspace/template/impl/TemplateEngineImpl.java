/**
 * File: TemplateEngine.java 
 *
 * Created on Jan 2, 2003
 */
package org.soulspace.template.impl;

import java.io.File;
import java.io.IOException;

import org.soulspace.template.TemplateEngine;
import org.soulspace.template.datasource.IDataSource;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.exception.UnknownTokenException;
import org.soulspace.template.method.impl.DynamicMethodRegistryImpl;
import org.soulspace.template.method.impl.MethodRegistryImpl;
import org.soulspace.template.method.impl.StaticMethodRegistryImpl;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.impl.TokenList;
import org.soulspace.template.tokenizer.impl.TokenizerImpl;
import org.soulspace.template.util.FileUtils;
import org.soulspace.template.value.ISymbolTable;

/**
 * Implementation of the TemplateEngine interface.
 * 
 * @author Ludger Solbach
 */
public class TemplateEngineImpl implements TemplateEngine {

	private IAstNode root;
	private Tokenizer tokenizer = new TokenizerImpl();
	private AstParserImpl parser = new AstParserImpl();

	/**
	 * Constructor
	 */
	public TemplateEngineImpl() {
		MethodRegistryImpl.setMethodRegisty(new StaticMethodRegistryImpl());
	}

	public TemplateEngineImpl(String registryType, String[] packages) {
		if(registryType != null && registryType.equals("dynamicRegistry")) {
			try {
				DynamicMethodRegistryImpl registry = new DynamicMethodRegistryImpl();
				if(packages != null) {
					for(String packageName : packages) {
						if(packageName != null) {
							registry.registerPackage(packageName);
						}
					}
				}
				MethodRegistryImpl.setMethodRegisty(registry);
			} catch (Exception e) {
				System.out.println("Error initializing dynamic method registry, using static registry");
				MethodRegistryImpl.setMethodRegisty(new StaticMethodRegistryImpl());
			}
		} else {
			MethodRegistryImpl.setMethodRegisty(new StaticMethodRegistryImpl());
		}
	}
	
	/**
	 * Load and parse the given template
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 */
	public void loadTemplate(String template) throws UnknownTokenException,
			SyntaxException {
		ITokenList tokenList = tokenizer.createTokenList();
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
	public void loadTemplate(File templateFile) throws UnknownTokenException,
			SyntaxException, IOException {
		ITokenList tokenList = tokenizer.createTokenList();
		tokenList = tokenize(tokenList, templateFile.getPath(), FileUtils.loadStringFromFile(templateFile));
		parse(tokenList);
	}

	/**
	 * Load and parse the given templates
	 * 
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 */
	public void loadTemplates(String[] templates)
			throws UnknownTokenException, SyntaxException {
		ITokenList tokenList = tokenizer.createTokenList();
		for(String template : templates) {
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
	public void loadTemplates(File[] templateFiles)
			throws UnknownTokenException, SyntaxException, IOException {
		ITokenList tokenList = tokenizer.createTokenList();
		for(File templateFile : templateFiles) {
			tokenList.setCurrentLine(1);
			tokenList = tokenize(tokenList, templateFile.getPath(), FileUtils.loadStringFromFile(templateFile));
		}
		parse(tokenList);
	}

	/**
	 * Lookup and the template files from the given search dirs and parse them.
	 * @param searchDirs
	 * @param templateFiles
	 * @throws UnknownTokenException
	 * @throws SyntaxException
	 * @throws IOException
	 */
	public void loadTemplates(File[] searchDirs, File[] templateFiles)
			throws UnknownTokenException, SyntaxException, IOException {

		ITokenList tokenList = tokenizer.createTokenList();
		// validate search dirs
		for(File searchDir : searchDirs) {
			if(!searchDir.exists() || !searchDir.isDirectory()) {
				throw new IOException("Search directory " + searchDir.getName() + " is invalid!");
			}
		}
		
		for(File templateFile : templateFiles) {
			
		}		
	}
	
	
	/**
	 * Generate output with the parsed template
	 * 
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate() throws SyntaxException,
			GenerateException {
		return root.generateSymbol().evaluate();		
	}
	
	/**
	 * Generate output for the given symbol table with the parsed template
	 * 
	 * @param symbolTable
	 *          to use for the generation
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate(ISymbolTable symbolTable) throws SyntaxException,
			GenerateException {

		root.setSymbolTable(symbolTable);
		return root.generateSymbol().evaluate();
	}

	/**
	 * Generate output for the given data source with the parsed template
	 * 
	 * @param dataSource
	 *          to use for the generation
	 * @return generated output
	 * @throws SyntaxException
	 * @throws GenerateException
	 */
	public String generate(IDataSource dataSource) throws SyntaxException,
			GenerateException {
		root.setSymbolTable(dataSource.getSymbolTable());
		return root.generateSymbol().evaluate();
	}
	
	private ITokenList tokenize(ITokenList tokenList, String name, String content) {
		tokenList.setTemplate(name);
		return tokenizer.tokenize(tokenList, content);
	}

	private boolean parse(ITokenList tokenList) {
		if (tokenList.size() == 0) {
			return false;
		}
		root = parser.parse(tokenList);
		return true;
	}
	

}