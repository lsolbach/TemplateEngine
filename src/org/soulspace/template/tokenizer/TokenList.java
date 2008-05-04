/*
 * Created on Apr 9, 2003
 *
 * 
 */
package org.soulspace.template.tokenizer;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.template.parser.SyntaxException;

/**
 * A Tokenlist stores a list of tokens and represents a part of the code for a Template.
 * 
 * @author Ludger Solbach
 *
 */
public class TokenList {

	private List<Token> tokenList = new ArrayList<Token>();
	private int pointer = 0;


	/**
	 * Constructor
	 */
	public TokenList() {
    super();
  }

  /**
   * Add a new token.
   * @param type
   */
	public void addToken(TokenType type) {
		tokenList.add(new Token(type));
	}

  /**
   * Add a new token
   * @param type
   * @param data
   */
	public void addToken(TokenType type, String data) {
		tokenList.add(new Token(type, data));
	}

  /**
   * Add a new token
   * @param token
   */
  public void addToken(Token token) {
    tokenList.add(token);
  }
  
  /**
   * Return the number of tokens in the token list
   * @return
   */
	public int size() {
		return tokenList.size();
	}

	/**
   * Return the token, the list pointer points to.
	 * @return Token
	 */
	public Token getToken() {
		if(pointer >= tokenList.size()) {
			return null;
		}
		return tokenList.get(pointer);	
	}

	/**
   * Increment the list pointer and return the token at that position
	 * @return Token
	 */
	public Token getNextToken() {
		if(tokenList.size() > ++pointer) {
      return tokenList.get(pointer);	
		} else {
			return null;
		}
	}

	/**
   * Increment the list pointer
   */
	public void skipToken() {
		pointer++;
	}
	
	/**
   * Check if there's a token at the position of the list pointer
	 * @return boolean
	 */
	public boolean hasToken() {
		return tokenList.size() > pointer;	
	}

	/**
   * Check if there's a token at the next position of the list pointer.
	 * @return boolean
	 */
	public boolean hasNextToken() {
		return tokenList.size() > (pointer + 1);	
	}

	/**
   * Return the token at the next list position
   * without incrementing the list pointer.
	 * @return TokenType
	 */
	public Token lookUpToken() {
		if(hasNextToken()) {
			return ((Token) tokenList.get(pointer + 1));
		}
		return null;
	}
	
  public Token lookUpLastToken() {
    if(tokenList.size() > 0) {
      return (Token) tokenList.get(tokenList.size() - 1);
    }
    return null;
  }
  
  /**
   * Return the Token with lookahead k without incrementing the list pointer.
   * @param k
   * @return
   */
  public Token lookUpToken(int k) {
  	if(tokenList.size() > pointer + k) {
  		return ((Token) tokenList.get(pointer + k));
  	}
  	return null;
  }
  
  /**
   * 
   * @return
   */
	public TokenType lookUpTokenType() {
		if(hasNextToken()) {
			return ((Token) tokenList.get(pointer + 1)).getType();
		}
		return null;
	}

	public boolean checkType(TokenType type) {
		return hasToken() && getToken().checkType(type);
	}
	
	public boolean checkNextType(TokenType type) {
		return hasNextToken() && lookUpToken().checkType(type);
	}

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
  	for(Token t : tList.tokenList) {
      System.out.print(t);
    }
    System.out.println("\n---");
  }

  public Object[] toArray() {
    return tokenList.toArray();
  }
  
}