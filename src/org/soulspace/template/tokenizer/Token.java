/*
 * Created on Apr 9, 2003
 *
 * 
 */
package org.soulspace.template.tokenizer;

import org.soulspace.template.parser.SyntaxException;


/**
 * Class for Tokens of the TemplateEngine.
 * A token represents a tokenized part of the template.
 * 
 * @author soulman
 */
public class Token {
	private TokenType type = null;
	private String data = "";		

	/**
	 * Constructor.
	 * @param type
	 */
	protected Token(TokenType type) {
		this.type = type; 
	}
	
	/**
	 * Constructor.
	 * @param type
	 * @param data
	 */
	protected Token(TokenType type, String data) {
		this.type = type; 
		this.data = data; 
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(32);
		sb.append("Token - Type: " + this.type.toString());
		if(data != null && !data.equals("")) {
			sb.append(", Data: " + this.data);
		}
		return sb.toString();
	}

	/**
	 * Returns the type of the token.
	 * @return TokenType
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * Returns the data of the token.
	 * @return String
	 */
	public String getData() {
		return data;
	}

  public void setData(String value) {
    data = value;
  }
  
  public boolean validateType(TokenType type) throws SyntaxException {
    if(!getType().equals(type)) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Expecting token of type " + type + ", found token of type " + getType());
    	if(getData() != null) {
    		sb.append(", token data: " + getData());
    	}
    	throw new SyntaxException(sb.toString());
    }
    return true;
  }
  
  public boolean checkType(TokenType type) {
    if(!getType().equals(type)) {
      return false;
    }
    return true;
  }
  

}