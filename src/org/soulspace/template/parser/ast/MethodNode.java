/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.template.parser.ast;

import java.util.List;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

public interface MethodNode extends AstNode {

	String getSignatureString();
	Signature getSignature();
	String getMethodName();
	
	void setReturnType(String returnType);
	void setReturnType(ValueType returnType);

	MethodNode getSuperMethod();
	void setSuperMethod(MethodNode node);
	Value callSuperMethod(List<Value> valueList);
	
	SymbolTable createSymbolTable(List<Value> valueList);
	Value generateSymbol(Environment environment, AstNode returnNode, List<Value> valueList);
}
