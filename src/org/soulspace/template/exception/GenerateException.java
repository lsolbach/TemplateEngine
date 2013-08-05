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
