/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
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
