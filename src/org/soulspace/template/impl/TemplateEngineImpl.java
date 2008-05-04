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
import org.soulspace.template.parser.GenerateException;
import org.soulspace.template.parser.SyntaxException;
import org.soulspace.template.parser.ast.IAstNode;
import org.soulspace.template.parser.ast.impl.AstParserImpl;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.Tokenizer;
import org.soulspace.template.tokenizer.TokenizerImpl;
import org.soulspace.template.tokenizer.UnknownTokenException;
import org.soulspace.template.util.FileUtils;

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
		parse(template);
	}

	public void loadTemplate(File templateFile) throws UnknownTokenException,
			SyntaxException, IOException {
		parse(FileUtils.loadStringFromFile(templateFile));
	}

	public void loadTemplates(String[] templates)
			throws UnknownTokenException, SyntaxException {
		StringBuilder sb = new StringBuilder(256);
		for(String template : templates) {
			sb.append(template);
		}
		parse(sb.toString());
	}

	public void loadTemplates(File[] templateFiles)
			throws UnknownTokenException, SyntaxException, IOException {
		StringBuilder sb = new StringBuilder(256);
		for(File templateFile : templateFiles) {
			sb.append(FileUtils.loadStringFromFile(templateFile));
		}
		parse(sb.toString());
	}

	/**
	 * Parse a template and convert it into tokens.
	 * Sets the token list.
	 * 
	 * @param template
	 *          Template to parse.
	 * @return false, if token list ist empty, true if token list contains tokens.
	 * @throws UnknownTokenException
	 */
	public boolean parse(String template) throws SyntaxException,
			UnknownTokenException {

		TokenList tokenList = tokenizer.tokenize(template);
		root = parser.parse(tokenList);

		if (tokenList.size() == 0) {
			return false;
		}
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