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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.soulspace.template.environment.Environment;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.Value;

/**
 * @author soulman
 * 
 */
public interface AstNode {
	
	AstNodeType getType();

	AstNode getParent();

	void setParent(AstNode parent);

	Collection<AstNode> getChildNodes();

	int getChildCount();

	AstNode getChild(int index);

	void addChildNode(AstNode node);

	String getData();

	void setData(String data);

	Environment getEnvironment();
	
	Value lookupSymbol(String name);

	Value generateValue(Environment environment);

	NumericValue asNumeric(Value symbol);

	StringValue asString(Value symbol);

	Map<String, List<MethodNode>> getMethodRegistry();

	MethodNode getMethodNode(AstNode callNode, String signature, List<Value> valueList);

	void addMethodNode(MethodNode node);

	// diagnostic information
	String getTemplate();
	int getLine();

}
