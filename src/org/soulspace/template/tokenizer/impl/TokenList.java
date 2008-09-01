/*
 * Created on Apr 9, 2003
 *
 * 
 */
package org.soulspace.template.tokenizer.impl;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.tokenizer.IToken;
import org.soulspace.template.tokenizer.ITokenList;
import org.soulspace.template.tokenizer.TokenType;

/**
 * A Tokenlist stores a list of tokens and represents a part of the code for a Template.
 * 
 * @author Ludger Solbach
 *
 */
public class TokenList implements ITokenList {

	private List<Token> tokenList = new ArrayList<Token>();
	private int pointer = 0;
	private int currentLine = 1;
	private String template = "";

	/**
	 * Constructor
	 */
	protected TokenList() {
    super();
  }

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#setCurrentLine(int)
	 */
	public void setCurrentLine(int line) {
		this.currentLine = line;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#incCurrentLines(int)
	 */
	public void incCurrentLines(int lines) {
		currentLine = currentLine + lines;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#incCurrentLine()
	 */
	public void incCurrentLine() {
		currentLine++;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#getCurrentLine()
	 */
	public int getCurrentLine() {
		return currentLine;
	}
	
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#getTemplate()
	 */
	public String getTemplate() {
		return template;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#setTemplate(java.lang.String)
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#addToken(org.soulspace.template.tokenizer.TokenType)
	 */
	public void addToken(TokenType type) {
		tokenList.add(new Token(type, "", template, currentLine));
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#addToken(org.soulspace.template.tokenizer.TokenType, java.lang.String)
	 */
	public void addToken(TokenType type, String data) {
		tokenList.add(new Token(type, data, template, currentLine));
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#size()
	 */
	public int size() {
		return tokenList.size();
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#getToken()
	 */
	public IToken getToken() {
		if(pointer >= tokenList.size()) {
			return null;
		}
		return tokenList.get(pointer);	
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#getNextToken()
	 */
	public IToken getNextToken() {
		if(tokenList.size() > ++pointer) {
      return tokenList.get(pointer);	
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#skipToken()
	 */
	public void skipToken() {
		pointer++;
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#hasToken()
	 */
	public boolean hasToken() {
		return tokenList.size() > pointer;	
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#hasNextToken()
	 */
	public boolean hasNextToken() {
		return tokenList.size() > (pointer + 1);	
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#lookUpToken()
	 */
	public IToken lookUpToken() {
		if(hasNextToken()) {
			return ((IToken) tokenList.get(pointer + 1));
		}
		return null;
	}
	
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#lookUpLastToken()
	 */
  public IToken lookUpLastToken() {
    if(tokenList.size() > 0) {
      return (IToken) tokenList.get(tokenList.size() - 1);
    }
    return null;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#lookUpToken(int)
	 */
  public IToken lookUpToken(int k) {
  	if(tokenList.size() > pointer + k) {
  		return ((IToken) tokenList.get(pointer + k));
  	}
  	return null;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#lookUpTokenType()
	 */
	public TokenType lookUpTokenType() {
		if(hasNextToken()) {
			return ((IToken) tokenList.get(pointer + 1)).getType();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#checkType(org.soulspace.template.tokenizer.TokenType)
	 */
	public boolean checkType(TokenType type) {
		return hasToken() && getToken().checkType(type);
	}
	
	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#checkNextType(org.soulspace.template.tokenizer.TokenType)
	 */
	public boolean checkNextType(TokenType type) {
		return hasNextToken() && lookUpToken().checkType(type);
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#validateType(org.soulspace.template.tokenizer.TokenType)
	 */
	public void validateType(TokenType type) throws SyntaxException {
  	StringBuilder sb = new StringBuilder();
		if(!hasToken()) {
    	sb.append("Expecting token of type " + type + " but there is no token");
    	throw new SyntaxException(sb.toString());
		}	else {
			getToken().validateType(type);
		}
	}
	
  /**
   * Print a Tokenlist to stdout.
   * @param tList
   */
  public static void printTokenList(TokenList tList) {
  	for(IToken t : tList.tokenList) {
      System.out.print(t);
    }
    System.out.println("\n---");
  }

  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.ITokenList#toArray()
	 */
  public Object[] toArray() {
    return tokenList.toArray();
  }
  
}