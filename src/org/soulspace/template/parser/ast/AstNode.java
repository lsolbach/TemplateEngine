/*
 * Created on Mar 10, 2005
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
