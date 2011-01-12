/*
 * Created on Mar 10, 2005
 */
package org.soulspace.template.parser.ast;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.soulspace.template.parser.ast.impl.ArgListNodeImpl;
import org.soulspace.template.value.NumericValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.Value;
import org.soulspace.template.value.ValueType;

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

	Value lookupSymbol(String name);

	Value getSymbol(AstNode node);

	SymbolTable getSymbolTable();

	void setSymbolTable(SymbolTable symbolTable);

	String getData();

	void setData(String data);

	Map<String, List<MethodNode>> getMethodRegistry();

	MethodNode getMethodNode(String signature, List<Value> valueList);

	void addMethodNode(MethodNode node);

	Value generateValue();

	NumericValue asNumeric(Value symbol);

	StringValue asString(Value symbol);

	// diagnostic information
	String getTemplate();
	int getLine();

}
