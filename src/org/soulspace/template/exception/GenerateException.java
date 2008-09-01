/**
 * File: GenerateException.java 
 *
 * Created on Dec 3, 2002
 */
package org.soulspace.template.exception;

/**
 * GenerateException is thrown on generation errors in the template engine.
 * 
 * @author soulman
 *
 */
public class GenerateException extends RuntimeException {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
	 * Constructor for GenerateException.
	 */
	public GenerateException() {
		super();
	}

	/**
	 * Constructor for GenerateException.
	 * @param message
	 */
	public GenerateException(String message) {
		super(message);
	}

	/**
	 * Constructor for GenerateException.
	 * @param message
	 * @param cause
	 */
	public GenerateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for GenerateException.
	 * @param cause
	 */
	public GenerateException(Throwable cause) {
		super(cause);
	}

}
