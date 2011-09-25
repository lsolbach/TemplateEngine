/*
 * Created on Apr 9, 2003
 *
 * 
 */
package org.soulspace.template.tokenizer.impl;

import org.soulspace.template.exception.SyntaxException;
import org.soulspace.template.tokenizer.Token;
import org.soulspace.template.tokenizer.TokenType;


/**
 * Class for Tokens of the TemplateEngine.
 * A token represents a tokenized part of the template.
 * 
 * @author soulman
 */
public class TokenImpl implements Token {
	private TokenType type = null;
	private String data = "";		
	private int line = 0;
	private String template = "";
	
	/**
	 * Constructor.
	 * @param type
	 */
	protected TokenImpl(TokenType type) {
		this.type = type; 
	}
	
	/**
	 * Constructor.
	 * @param type
	 * @param data
	 */
	protected TokenImpl(TokenType type, String data, String template, int line) {
		this.type = type; 
		this.data = data;
		this.line = line;
		this.template = template;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(32);
		sb.append("TokenImpl[Type=" + type.toString());
		sb.append(",Template=" + template);
		sb.append(",Line=" + line);
		if(data != null && !data.equals("")) {
			sb.append(",Data=" + data);
		}
		sb.append("]");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#getType()
	 */
	public TokenType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#getData()
	 */
	public String getData() {
		return data;
	}

  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#setData(java.lang.String)
	 */
  public void setData(String value) {
    data = value;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#getLine()
	 */
  public int getLine() {
  	return line;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#setLine(int)
	 */
  public void setLine(int line) {
  	this.line = line;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#getTemplate()
	 */
  public String getTemplate() {
  	return template;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#validateType(org.soulspace.template.tokenizer.TokenType)
	 */
  public boolean validateType(TokenType type) throws SyntaxException {
    if(!getType().equals(type)) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Expecting token of type " + type + ", found token " + this
    			+ "! Template " + this.template + ", line " + this.line);
    	throw new SyntaxException(sb.toString());
    }
    return true;
  }
  
  /* (non-Javadoc)
	 * @see org.soulspace.template.tokenizer.Token#checkType(org.soulspace.template.tokenizer.TokenType)
	 */
  public boolean checkType(TokenType type) {
    if(!getType().equals(type)) {
      return false;
    }
    return true;
  }
  
}