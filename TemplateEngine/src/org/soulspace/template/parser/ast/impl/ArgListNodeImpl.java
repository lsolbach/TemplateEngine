/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.parser.ast.impl;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.exception.GenerateException;
import org.soulspace.template.parser.ast.AstNode;
import org.soulspace.template.parser.ast.AstNodeType;
import org.soulspace.template.value.Value;

public class ArgListNodeImpl extends AbstractAstNode {

  public ArgListNodeImpl() {
    this(null);
  }

  public ArgListNodeImpl(AstNode parent) {
    super(parent);
    setType(AstNodeType.ARG_LIST);
  }

	public Value generateValue(Environment environment) {
		setEnvironment(environment);
		throw new GenerateException("Method generateSymbol() must not be called on ArgListNodeImpl! Template " + getTemplate() + ", line " + getLine());
	}

}
