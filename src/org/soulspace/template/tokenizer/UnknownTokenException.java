/**
 * File: UnknownTokenException.java 
 *
 * Created on Dec 3, 2002
 */
package org.soulspace.template.tokenizer;

/**
 * UnknownTokenException is thrown on parse errors in the TemplateEngine.
 * 
 * @author soulman
 *
 */
public class UnknownTokenException extends RuntimeException {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
	 * Constructor for UnknownTokenException.
	 */
	public UnknownTokenException() {
		super();
	}

	/**
	 * Constructor for UnknownTokenException.
	 * @param message
	 */
	public UnknownTokenException(String message) {
		super(message);
	}

	/**
	 * Constructor for UnknownTokenException.
	 * @param message
	 * @param cause
	 */
	public UnknownTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for UnknownTokenException.
	 * @param cause
	 */
	public UnknownTokenException(Throwable cause) {
		super(cause);
	}

}
