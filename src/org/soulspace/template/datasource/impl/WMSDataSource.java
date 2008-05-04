/*
 * Created on Feb 22, 2003
 *
 */
package org.soulspace.template.datasource.impl;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.soulspace.template.datasource.IDataSource;
import org.soulspace.template.symbols.ISymbolTable;
import org.soulspace.template.symbols.impl.SymbolTable;

/**
 * @author soulman
 * 
 * Parser for WMS Data Files
 */
public class WMSDataSource implements IDataSource {
	// TODO make static?

	static String REGEX_1 = "(?:(?:^;.*?$)|(?:^\\#(.*)\\#$)|(\\S.*))";

	ISymbolTable symbolTable = null;
	String input = "";
	boolean parsed = false;

	
	/**
	 * Constructor
	 * @param input
	 */
	public WMSDataSource(String input) {
		symbolTable = new SymbolTable();
		this.input = input;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.dataloader.IDataSource#getSymbolTable()
	 */
	public ISymbolTable getSymbolTable() {
		if(!parsed) {
			parsed = parse();
		}
		return symbolTable;
	}

	/**
	 * Parse wms data into symbol table
	 * @return boolean
	 */
	boolean parse() {
		PatternCompiler compiler = new Perl5Compiler();
		PatternMatcher matcher = new Perl5Matcher();
		Pattern pattern;
		PatternMatcherInput mInput;
		MatchResult result;

		String key = null;
		String content = null;
		String symbolName = null;
		StringBuffer buffer = new StringBuffer(64);

		// build matcher
		try {
			pattern = compiler.compile(REGEX_1, Perl5Compiler.MULTILINE_MASK);
		} catch(MalformedPatternException e) {
			System.out.println("Bad pattern.");
			System.out.println(e.getMessage());
			return false;
		}

		// build input
		mInput = new PatternMatcherInput(this.input);

		// loop over matches
		while(matcher.contains(mInput, pattern)) {
			result = matcher.getMatch();

			key = result.group(1);
			content = result.group(2);

			if(key != null && !key.equalsIgnoreCase("")) {
				// is this the first symbol?
				if(symbolName != null) {
					// no, so add it to the symbol table
					symbolTable.addNewStringSymbol(symbolName, buffer.toString());					
					buffer = new StringBuffer(64);
				}
				// set symbol name
				symbolName = key;
			}
			if(content != null && !content.equalsIgnoreCase("")) {
				// add content to buffer
				buffer.append(content + "\n");
			}
		}
		// add last symbol
		if(symbolName != null) {
			symbolTable.addNewStringSymbol(symbolName, buffer.toString());								
		}
		
		return true;	
	}

}
