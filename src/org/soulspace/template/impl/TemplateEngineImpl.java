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

	}

	public void loadTemplate(String template) throws UnknownTokenException,
			SyntaxException {
		ITokenList tokenList = tokenizer.createTokenList();
		tokenList = tokenize(tokenList, "Template", template);
		parse(tokenList);
	}

	public void loadTemplate(File templateFile) throws UnknownTokenException,
			SyntaxException, IOException {
		ITokenList tokenList = tokenizer.createTokenList();
		tokenList = tokenize(tokenList, templateFile.getName(), FileUtils.loadStringFromFile(templateFile));
		parse(tokenList);
	}

	public void loadTemplates(String[] templates)
			throws UnknownTokenException, SyntaxException {
		ITokenList tokenList = tokenizer.createTokenList();
		for(String template : templates) {
			tokenList = tokenize(tokenList, "Template", template);
		}
		parse(tokenList);
	}

	public void loadTemplates(File[] templateFiles)
			throws UnknownTokenException, SyntaxException, IOException {
		ITokenList tokenList = tokenizer.createTokenList();
		for(File templateFile : templateFiles) {
			tokenList.setCurrentLine(1);
			tokenList = tokenize(tokenList, templateFile.getName(), FileUtils.loadStringFromFile(templateFile));
		}
		parse(tokenList);
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
	
}