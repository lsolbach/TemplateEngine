/**
 * File: SyntaxException.java 
 *
 * Created on Dec 3, 2002
 */
package org.soulspace.template.exception;

/**
 * SyntaxException is thrown on syntax errors while parsing templates
 * in the template engine.
 * 
 * @author soulman
 */
public class SyntaxException extends RuntimeException {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
	 * Constructor for SyntaxException.
	 */
	public SyntaxException() {
		super();
	}

	/**
	 * Constructor for SyntaxException.
	 * @param message
	 */
	public SyntaxException(String message) {
		super(message);
	}

	/**
	 * Constructor for SyntaxException.
	 * @param message
	 * @param cause
	 */
	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for SyntaxException.
	 * @param cause
	 */
	public SyntaxException(Throwable cause) {
		super(cause);
	}

}
