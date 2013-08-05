/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.datasource.impl;

public class DataSourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataSourceException() {
		super();
	}

	public DataSourceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DataSourceException(String arg0) {
		super(arg0);
	}

	public DataSourceException(Throwable arg0) {
		super(arg0);
	}

}
