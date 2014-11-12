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
