/*
 * Created on Apr 9, 2003
 *
 * 
 */
package org.soulspace.template.tokenizer.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenList;
import org.soulspace.template.tokenizer.TokenType;

/**
 * A Tokenlist stores a list of tokens and represents a part of the code for a
 * Template.
 * 
 * @author Ludger Solbach
 * 
 */
public class TokenListImpl implements TokenList {

	private List<TokenImpl> tokenList = new ArrayList<TokenImpl>();
	private int pointer = 0;
	private int currentLine = 1;
	private String template = "";

	/**
	 * Constructor
	 */
	protected TokenListImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#setCurrentLine(int)
	 */
	public void setCurrentLine(int line) {
		this.currentLine = line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#incCurrentLines(int)
	 */
	public void incCurrentLines(int lines) {
		currentLine = currentLine + lines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#incCurrentLine()
	 */
	public void incCurrentLine() {
		currentLine++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#getCurrentLine()
	 */
	public int getCurrentLine() {
		return currentLine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#getTemplate()
	 */
	public String getTemplate() {
		return template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#setTemplate(java.lang.String)
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#addToken(org.soulspace.template
	 * .tokenizer.TokenType)
	 */
	public void addToken(TokenType type) {
		tokenList.add(new TokenImpl(type, "", template, currentLine));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#addToken(org.soulspace.template
	 * .tokenizer.TokenType, java.lang.String)
	 */
	public void addToken(TokenType type, String data) {
		tokenList.add(new TokenImpl(type, data, template, currentLine));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#size()
	 */
	public int size() {
		return tokenList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#getToken()
	 */
	public Token getToken() {
		if (pointer >= tokenList.size()) {
			return null;
		}
		return tokenList.get(pointer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#getNextToken()
	 */
	public Token getNextToken() {
		if (tokenList.size() > ++pointer) {
			return tokenList.get(pointer);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#skipToken()
	 */
	public void skipToken() {
		pointer++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#hasToken()
	 */
	public boolean hasToken() {
		return tokenList.size() > pointer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#hasNextToken()
	 */
	public boolean hasNextToken() {
		return tokenList.size() > (pointer + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#lookUpToken()
	 */
	public Token lookUpToken() {
		if (hasNextToken()) {
			return ((Token) tokenList.get(pointer + 1));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#lookUpLastToken()
	 */
	public Token lookUpLastToken() {
		if (tokenList.size() > 0) {
			return (Token) tokenList.get(tokenList.size() - 1);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#lookUpToken(int)
	 */
	public Token lookUpToken(int k) {
		if (tokenList.size() > pointer + k) {
			return ((Token) tokenList.get(pointer + k));
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#lookUpTokenType()
	 */
	public TokenType lookUpTokenType() {
		if (hasNextToken()) {
			return ((Token) tokenList.get(pointer + 1)).getType();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#checkType(org.soulspace.template
	 * .tokenizer.TokenType)
	 */
	public boolean checkType(TokenType type) {
		return hasToken() && getToken().checkType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#checkNextType(org.soulspace
	 * .template.tokenizer.TokenType)
	 */
	public boolean checkNextType(TokenType type) {
		return hasNextToken() && lookUpToken().checkType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.soulspace.template.tokenizer.TokenList#validateType(org.soulspace
	 * .template.tokenizer.TokenType)
	 */
	public void validateType(TokenType type) throws SyntaxException {
		StringBuilder sb = new StringBuilder();
		if (!hasToken()) {
			sb.append("Expecting token of type " + type
					+ " but there is no token");
			throw new SyntaxException(sb.toString());
		} else {
			getToken().validateType(type);
		}
	}

	/**
	 * Print a Tokenlist to stdout.
	 * 
	 * @param tList
	 */
	public static void printTokenList(TokenListImpl tList) {
		for (Token t : tList.tokenList) {
			System.out.print(t);
		}
		System.out.println("\n---");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soulspace.template.tokenizer.TokenList#toArray()
	 */
	public Object[] toArray() {
		return tokenList.toArray();
	}

}